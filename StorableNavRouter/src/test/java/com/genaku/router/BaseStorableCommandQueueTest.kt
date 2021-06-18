package com.genaku.router

import app.cash.turbine.test
import com.genaku.navrouter.BaseStorableCommandQueue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class BaseStorableCommandQueueTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Test
    fun `проверим, отправляются ли команды через flow`() = runBlockingTest {
        val q = BaseStorableCommandQueue<Comm>(testDispatcher)
        q.send(First)
        q.send(Second)
        q.commandFlow.test {
            assertEquals(First, expectItem())
            assertEquals(Second, expectItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `проверим, получаем ли мы список неотработанных команд`() = testScope.runBlockingTest {
        val q = BaseStorableCommandQueue<Comm>(testDispatcher)
        q.send(First)
        q.send(Second)
        val toStore = q.getCommandsToStore()
        assertEquals(listOf(First, Second), toStore)
        q.commandFlow.test { cancelAndIgnoreRemainingEvents() }
    }

    @Test
    fun `getCommandsToStore должен вернуть список неотработанных команд и удалить их из очереди`() =
        testScope.runBlockingTest {
            val q = BaseStorableCommandQueue<Comm>(testDispatcher)
            q.send(First)
            q.send(Second)
            val toStore = q.getCommandsToStore()
            assertEquals(listOf(First, Second), toStore)
            q.commandFlow.test { cancelAndIgnoreRemainingEvents() }

            q.send(Second)
            q.send(First)
            q.commandFlow.test {
                assertEquals(Second, expectItem())
                assertEquals(First, expectItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `setCommandsFromStore в пустую очередь команд должен добавить новые команды`() =
        testScope.runBlockingTest {
            val q = BaseStorableCommandQueue<Comm>(testDispatcher)
            q.setCommandsFromStore(listOf(First, Second))
            q.commandFlow.test {
                assertEquals(First, expectItem())
                assertEquals(Second, expectItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `после восстановления очереди через setCommandsFromStore добавление новой команды должно поместить ее в конец очереди`() =
        testScope.runBlockingTest {
            val q = BaseStorableCommandQueue<Comm>(testDispatcher)
            q.setCommandsFromStore(listOf(First, Second))
            q.send(Third)
            q.commandFlow.test {
                assertEquals(First, expectItem())
                assertEquals(Second, expectItem())
                assertEquals(Third, expectItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `после восстановления очереди через setCommandsFromStore проверим, что они добавились и отдаются через flow, и затем добавление команды работает корректно`() =
        testScope.runBlockingTest {
            val q = BaseStorableCommandQueue<Comm>(testDispatcher)
            q.setCommandsFromStore(listOf(First, Second))
            q.commandFlow.test {
                assertEquals(First, expectItem())
                assertEquals(Second, expectItem())
                cancelAndIgnoreRemainingEvents()
            }
            q.send(Third)
            q.commandFlow.test {
                assertEquals(Third, expectItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `если до восстановления состояния успели отправить команду, она должна оказаться в конце, после сохраненного списка команд`() =
        testScope.runBlockingTest {
            val q = BaseStorableCommandQueue<Comm>(testDispatcher)
            q.send(Third)
            q.setCommandsFromStore(listOf(First, Second))
            q.commandFlow.test {
                assertEquals(First, expectItem())
                assertEquals(Second, expectItem())
                assertEquals(Third, expectItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

    sealed class Comm : RouterCommand
    object First : Comm()
    object Second : Comm()
    object Third : Comm()
}