package com.genaku.navrouter

import android.os.Bundle
import android.util.Log
import com.genaku.router.CommandQueue
import com.genaku.router.RouterCommand
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.Serializable
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean

open class BaseStorableCommandQueue<C : RouterCommand>(
    private val key: String = DEFAULT_KEY,
    dispatcher: CoroutineDispatcher = Dispatchers.Default
) : CommandQueue<C>, StorableInstanceState {

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
//            log("wait $DELAY")
            delay(DELAY)
        }
    }

    private fun canGetCommand(): Boolean = (canSend.get() && !commands.isEmpty())

    override fun send(command: C) {
        log("send command $command")
        commands.add(command)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val data = savedInstanceState.getSerializable(key) as BaseStorableCommandQueue<C>.QueueData?
            ?: return
        log("restore ${data.commands}")
        setCommandsFromStore(data.commands)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val data = QueueData(getCommandsToStore())
        outState.putSerializable(key, data)
        log("save ${data.commands}")
    }

    private fun getCommandsToStore(): List<C> {
        canSend.set(false)
        val result = commands.toList()
        commands.clear()
        return result
    }

    private fun setCommandsFromStore(storedCommands: List<C>) {
        val newCommands = getCommandsToStore()
        val allCommands = mutableListOf<C>()
        allCommands.addAll(storedCommands)
        allCommands.addAll(newCommands)
        commands.addAll(allCommands)
        canSend.set(true)
    }

    private fun log(msg: String) {
        Log.d("TAF", "[${Thread.currentThread()}] $msg")
    }

    inner class QueueData(val commands: List<C>) : Serializable

    companion object {
        private const val DELAY = 300L
        private const val DEFAULT_KEY = "RouterCommands"
    }
}