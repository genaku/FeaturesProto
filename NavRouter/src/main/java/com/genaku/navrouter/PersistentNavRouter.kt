package com.genaku.navrouter

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.genaku.storablerouter.PersistentCommandQueue
import com.genaku.storablerouter.PersistentInstanceState
import com.genaku.storablerouter.PersistentRouterScreens
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PersistentNavRouter(
    commandsKey: String = PersistentCommandQueue.DEFAULT_KEY,
    screensKey: String = PersistentRouterScreens.DEFAULT_KEY,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : NavRouter(
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

    fun connect(
        lifecycleOwner: LifecycleOwner,
        navController: NavController
    ) {
        lifecycleOwner.lifecycleScope.launch {
            commandFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.RESUMED)
                .collect {
                    processNavigation(it, navController)
                }
        }
    }
}