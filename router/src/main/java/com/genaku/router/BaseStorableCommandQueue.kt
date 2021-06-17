package com.genaku.router

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean

open class BaseStorableCommandQueue<C : RouterCommand>(dispatcher: CoroutineDispatcher = Dispatchers.Default) : StorableCommandQueue<C> {

    protected var canSend = AtomicBoolean(true)

    protected val commands: Queue<C> = ConcurrentLinkedQueue()

    override val commandFlow: Flow<C> = flow {
        log("create flow")
        while (true) {
            tryGetCommand()?.run {
                log("emit command $this")
                emit(this)
                commands.remove()
            } ?: wait()
        }
    }.flowOn(dispatcher)

    private fun tryGetCommand(): C? {
        log("try get command")
        if (canGetCommand()) {
            return commands.peek()
        }
        return null
    }

    private suspend fun wait() {
        log("start waitAsync")
        while (!canGetCommand()) {
            log("wait $DELAY")
            delay(DELAY)
        }
    }

    private fun canGetCommand(): Boolean = (canSend.get() && !commands.isEmpty())

    override fun send(command: C) {
        log("send command $command")
        commands.add(command)
    }

    override fun getCommandsToStore(): List<C> {
        canSend.set(false)
        val result = commands.toList()
        commands.clear()
        canSend.set(true)
        return result
    }

    override fun setCommandsFromStore(storedCommands: List<C>) {
        val newCommands = getCommandsToStore()
        canSend.set(false)
        val allCommands = mutableListOf<C>()
        allCommands.addAll(storedCommands)
        allCommands.addAll(newCommands)
        commands.addAll(allCommands)
        canSend.set(true)
    }

    private fun log(msg: String) {
//        println("TAF [${Thread.currentThread()}] $msg")
    }

    companion object {
        private const val DELAY = 300L
    }
}