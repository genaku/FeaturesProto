package com.genaku.navrouter

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.genaku.router.AbstractNavRouter
import com.genaku.router.AbstractRouter
import com.genaku.router.CommandQueue
import com.genaku.router.RouterScreens
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

/**
 * Navigation router based on Navigation Component
 *
 * @author Gena Kuchergin
 */
open class NavRouter(
    commandQueue: CommandQueue<NavCommand>,
    routerScreens: RouterScreens<NavScreen>
) : AbstractNavRouter<NavScreen>(commandQueue, routerScreens) {

    override fun getStartCommand(screen: NavScreen, uuid: UUID): NavCommand =
        Open(screen.destinationResId, uuid)

    override fun getFinishCommand(uuid: UUID): NavCommand = Back

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

