package com.genaku.router

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.genaku.navrouter.Back
import com.genaku.navrouter.BackAction
import com.genaku.navrouter.NavCommand
import com.genaku.navrouter.Open
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

abstract class AbstractNavRouter<S : RouterScreen>(
    commandQueue: CommandQueue<NavCommand>,
    routerScreens: RouterScreens<S>
) : AbstractRouter<S, NavCommand>(commandQueue, routerScreens) {

    fun connect(
        LifecycleOwner: LifecycleOwner,
        navController: NavController
    ) = LifecycleOwner.lifecycleScope.launch {
        commandFlow.flowWithLifecycle(LifecycleOwner.lifecycle, Lifecycle.State.RESUMED)
            .collect {
                Log.d("TAF", "collect $it")
                when (it) {
                    Back -> navController.navigateUp()
                    is Open -> navController.navigate(it.destinationResId, uidToBundle(it.uuid))
                    is BackAction -> navController.navigate(it.actionResId)
                }
            }
    }
}

internal const val NAV_UID = "navUid"

fun getUid(arguments: Bundle?): UUID? =
    arguments?.getSerializable(NAV_UID) as UUID?

fun uidToBundle(uuid: UUID) = Bundle().apply {
    putSerializable(NAV_UID, uuid)
}