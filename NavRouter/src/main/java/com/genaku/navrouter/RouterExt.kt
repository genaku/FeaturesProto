package com.genaku.navrouter

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.genaku.router.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
//
//fun CommandFlow<NavCommand>.connect(
//    LifecycleOwner: LifecycleOwner,
//    navController: NavController
//) = LifecycleOwner.lifecycleScope.launch {
//    commandFlow.flowWithLifecycle(LifecycleOwner.lifecycle, Lifecycle.State.RESUMED)
//        .collect {
//            Log.d("TAF", "collect $it")
//            when (it) {
//                Back -> navController.navigateUp()
//                is Open -> navController.navigate(it.destinationResId, uidToBundle(it.uuid))
//                is BackAction -> navController.navigate(it.actionResId)
//            }
//        }
//}
//
//internal const val NAV_UID = "navUid"
//
//fun getUid(arguments: Bundle?): UUID? =
//    arguments?.getSerializable(NAV_UID) as UUID?
//
//fun uidToBundle(uuid: UUID) = Bundle().apply {
//    putSerializable(NAV_UID, uuid)
//}