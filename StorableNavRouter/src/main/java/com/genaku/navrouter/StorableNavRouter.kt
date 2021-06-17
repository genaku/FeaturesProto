package com.genaku.navrouter

import android.os.Bundle
import android.util.Log
import com.genaku.router.BaseStorableCommandQueue
import com.genaku.router.StorableCommandQueue
import java.io.Serializable

class StorableNavRouter(commandQueue: StorableCommandQueue<NavCommand> = BaseStorableCommandQueue()): NavRouter(commandQueue),
    StorableInstanceState {

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val data = savedInstanceState.getSerializable(KEY) as NavRouterData? ?: return
        Log.d("TAF", "restore $data")
        setScreens(data.screens)
        commandQueue.setCommandsFromStore(data.commands)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val data = NavRouterData(getAllScreens(), commandQueue.getCommandsToStore())
        outState.putSerializable(KEY, data)
        Log.d("TAF", "save $data")
    }

    data class NavRouterData(val screens: Map<Long, NavScreen>, val commands: List<NavCommand>) :
        Serializable

    companion object {
        private const val KEY = "NavRouter"
    }
}