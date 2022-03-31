package com.genaku.navrouter

import android.os.Bundle
import com.genaku.storablerouter.PersistentCommandQueue
import com.genaku.storablerouter.PersistentInstanceState
import com.genaku.storablerouter.PersistentRouterScreens
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PersistentNavFeatureRouter(
    commandsKey: String = PersistentCommandQueue.DEFAULT_KEY,
    screensKey: String = PersistentRouterScreens.DEFAULT_KEY,
    dispatcher: CoroutineDispatcher = Dispatchers.Default
) : NavFeatureRouter(
    commandQueue = PersistentCommandQueue(commandsKey, dispatcher),
    routerScreens = PersistentRouterScreens(screensKey)
), PersistentInstanceState {

    private val persistentCommandQueue = commandQueue as PersistentCommandQueue
    private val persistentRouterScreens = routerScreens as PersistentRouterScreens

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        persistentCommandQueue.onRestoreInstanceState(savedInstanceState)
        persistentRouterScreens.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        persistentCommandQueue.onSaveInstanceState(outState)
        persistentRouterScreens.onSaveInstanceState(outState)
    }
}