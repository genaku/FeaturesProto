package com.genaku.navrouter

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.genaku.router.CommandFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

fun CommandFlow<NavCommand>.connect(
    lifecycleOwner: LifecycleOwner,
    navController: NavController
) {
    lifecycleOwner.lifecycleScope.launch {
        commandFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.RESUMED)
            .collect { command ->
                Log.d("TAF", "collect $command")
                when (command) {
                    Back -> navController.navigateUp()
                    is Open -> navController.navigate(command.destinationResId, uidToBundle(command.uuid))
                    is BackAction -> navController.navigate(command.actionResId)
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