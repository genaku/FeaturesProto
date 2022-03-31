package com.genaku.navrouter

import android.os.Bundle
import android.util.Log
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

//    fun connect(
//        LifecycleOwner: LifecycleOwner,
//        navController: NavController
//    ) = LifecycleOwner.lifecycleScope.launch {
//        commandFlow.flowWithLifecycle(LifecycleOwner.lifecycle, androidx.lifecycle.Lifecycle.State.RESUMED)
//            .collect {
//                Log.d("TAF", "collect $it")
//                when (it) {
//                    Back -> navController.navigateUp()
//                    is Open -> navController.navigate(it.destinationResId, uidToBundle(it.uuid))
//                    is BackAction -> navController.navigate(it.actionResId)
//                }
//            }
//    }
}