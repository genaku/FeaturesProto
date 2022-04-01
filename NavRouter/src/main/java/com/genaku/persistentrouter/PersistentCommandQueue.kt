package com.genaku.persistentrouter

import android.os.Bundle
import android.util.Log
import com.genaku.router.RouterCommand
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.io.Serializable

open class PersistentCommandQueue<C : RouterCommand>(
    private val key: String = DEFAULT_KEY,
    dispatcher: CoroutineDispatcher = Dispatchers.Default
) : PersistentCommandFlow<C>(dispatcher), PersistentInstanceState {

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val data = savedInstanceState.getSerializable(key) as? QueueData<C>
        if (data == null) {
            addCommandsAndResumeFlow(emptyList())
        } else {
            log("restore ${data.commands}")
            addCommandsAndResumeFlow(data.commands)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val data = QueueData(pullAllCommandsAndPauseFlow())
        log("save ${data.commands}")
        if (data.commands.isNotEmpty()) {
            outState.putSerializable(key, data)
        }
    }

    private fun log(msg: String) {
        Log.d("TAF", "[${Thread.currentThread()}] $msg")
    }

    class QueueData<C>(val commands: List<C>) : Serializable

    companion object {
        const val DEFAULT_KEY = "RouterCommands"
    }
}

