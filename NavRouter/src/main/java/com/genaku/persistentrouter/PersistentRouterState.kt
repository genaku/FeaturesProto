package com.genaku.persistentrouter

import android.os.Bundle
import com.example.navrouter_api.router.RouterScreen
import com.genaku.router.RouterCommand
import com.genaku.router.RouterState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PersistentRouterState<S : RouterScreen, C: RouterCommand>(
    name: String,
    dispatcher: CoroutineDispatcher = Dispatchers.Default
) : RouterState<S, C>, PersistentInstanceState {

    override val commandQueue = PersistentCommandQueue<C>(name, dispatcher)
    override val routerScreens = PersistentRouterScreens<S>(name)

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        commandQueue.onRestoreInstanceState(savedInstanceState)
        routerScreens.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        commandQueue.onSaveInstanceState(outState)
        routerScreens.onSaveInstanceState(outState)
    }
}