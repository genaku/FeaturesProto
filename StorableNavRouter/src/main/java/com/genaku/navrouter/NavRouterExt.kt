package com.genaku.navrouter

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.genaku.router.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.Serializable

fun <T : RouterCommand> CommandFlow<T>.connectTo(
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController
) {
    lifecycleScope.launchWhenResumed {
        Log.d("TAF", "start collecting")
        commandFlow.collect {
            Log.d("TAF", "collect $it")
            when (it) {
                Back -> navController.navigateUp()
                is Open -> navController.navigate(it.destinationResId, uidToBundle(it.uid))
                is BackAction -> navController.navigate(it.actionResId)
            }
        }
    }
}

internal const val NAV_UID = "navUid"

fun getUid(arguments: Bundle?): Long? =
    arguments?.getLong(NAV_UID)

fun uidToBundle(uid: Long) = Bundle().apply {
    putLong(NAV_UID, uid)
}

//fun RouterCommand.toParcel(): Serializable = TODO()
//
//open class RestorableCommandQueue<C : RouterCommand>(
//    private val coroutineScope: CoroutineScope
//) : BaseCommandQueue<C>(), Restorable {
//
//    override fun save(bundle: Bundle) {
//        coroutineScope.launch {
//            val commands = channel.toList()
//
//            val json = Json.encodeToString(RouterCommands(commands))
//            bundle
//        }
//    }
//
//    override fun restore(bundle: Bundle?) {
//        bundle ?: return
//    }
//}

//@kotlinx.serialization.Serializable
//data class RouterCommands(val commands: List<RouterCommand>)
//
//interface Restorable {
//    fun save(bundle: Bundle)
//    fun restore(bundle: Bundle?)
//}