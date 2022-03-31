package com.genaku.router

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import com.genaku.navrouter.Back
import com.genaku.navrouter.BackAction
import com.genaku.navrouter.NavCommand
import com.genaku.navrouter.Open
import java.util.*

abstract class AbstractNavRouter<S : RouterScreen>(
    commandQueue: CommandQueue<NavCommand>,
    routerScreens: RouterScreens<S>
) : AbstractRouter<S, NavCommand>(commandQueue, routerScreens) {

    protected open fun processNavigation(command: NavCommand, navController: NavController) {
        Log.d("TAF", "collect $command")
        when (command) {
            Back -> navController.navigateUp()
            is Open -> navController.navigate(command.destinationResId, uidToBundle(command.uuid))
            is BackAction -> navController.navigate(command.actionResId)
        }
    }
}

internal const val NAV_UID = "navUid"

fun getUid(arguments: Bundle?): UUID? =
    arguments?.getSerializable(NAV_UID) as UUID?

fun uidToBundle(uuid: UUID) = Bundle().apply {
    putSerializable(NAV_UID, uuid)
}