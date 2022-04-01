package com.genaku.persistentrouter

import android.os.Bundle
import com.genaku.router.RouterCommand
import com.genaku.router.RouterScreen
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PersistentRouterState<C: RouterCommand, S : RouterScreen>(
    commandsKey: String = PersistentCommandQueue.DEFAULT_KEY,
    screensKey: String = PersistentRouterScreens.DEFAULT_KEY,
    dispatcher: CoroutineDispatcher = Dispatchers.Default
) : PersistentInstanceState {

    val commandQueue = PersistentCommandQueue<C>(commandsKey, dispatcher)
    val routerScreens = PersistentRouterScreens<S>(screensKey)

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        commandQueue.onRestoreInstanceState(savedInstanceState)
        routerScreens.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        commandQueue.onSaveInstanceState(outState)
        routerScreens.onSaveInstanceState(outState)
    }
}