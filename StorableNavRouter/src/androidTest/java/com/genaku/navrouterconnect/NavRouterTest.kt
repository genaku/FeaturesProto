package com.genaku.navrouterconnect

import android.os.Bundle
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.genaku.navrouter.AbstractNavScreen
import com.genaku.navrouter.NavCommand
import com.genaku.navrouter.NavRouter
import com.genaku.navrouter.NavScreen
import com.genaku.router.BaseCommandQueue
import com.genaku.router.ScreenParams
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.*
import kotlin.time.ExperimentalTime

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavRouterTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.genaku.navrouterconnect.test", appContext.packageName)
    }

    @ExperimentalTime
//    @Test
    fun ddd() {
        val screen = StartScreen(StartScreenParams(1L))

        val channel = Channel<NavScreen>(Channel.UNLIMITED)
        runBlocking {
            channel.trySend(screen)
            val l = channel.toList()
            assertEquals(emptyList<NavScreen>(), l)
        }

//        runBlocking {
//            flowOf("one", "two").test {
//                assertEquals("one", expectItem())
//                assertEquals("two", expectItem())
//                expectComplete()
//            }
//            val flow = flowOf(screen)
//            val allCommands = mutableListOf<StartScreen>()
//            runBlocking(coroutineContext) {
//                flow.toList(allCommands)
//            }
////            flow.toList(allCommands)
//            assertEquals(listOf(screen), allCommands)
//
//        }
    }

//    @Test
//    fun dsd() {
//        val router = NavRouter(BaseCommandQueue())
//        runBlocking {
//            router.start(StartScreen(StartScreenParams(1L)))
//            val bundle = Bundle()
//            router.onSaveInstanceState(bundle)
//            val s = bundle.getSerializable("NavRouter") as NavRouter.NavRouterData
//            assertEquals(NavRouter.NavRouterData(emptyMap(), emptyList()), s)
//        }
//    }

}

class StartScreen(override val params: StartScreenParams) : AbstractNavScreen(0)

class StartScreenParams(val dx: Long) : ScreenParams

val queue: Queue<NavCommand> = LinkedList<NavCommand>()