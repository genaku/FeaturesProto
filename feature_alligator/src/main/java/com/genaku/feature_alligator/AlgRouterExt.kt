package com.genaku.feature_alligator

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import com.genaku.alligatorrouter.Back
import com.genaku.alligatorrouter.Open
import com.genaku.alligatorrouter.Replace
import com.genaku.alligatorrouter.ResetTo
import com.genaku.router.CommandFlow
import com.genaku.router.RouterCommand
import kotlinx.coroutines.flow.collect
import me.aartikov.alligator.AndroidNavigator

fun <T : RouterCommand> CommandFlow<T>.connectTo(
    lifecycleScope: LifecycleCoroutineScope,
    navigator: AndroidNavigator
) = lifecycleScope.launchWhenResumed {
    Log.d("TAF", "start collecting")
    commandFlow.collect {
        Log.d("TAF", "collect $it")
        when (it) {
            Back -> navigator.goBack()
            is Open -> navigator.goForward(it.screen.apply { uuid = it.uuid })
            is ResetTo -> navigator.reset(it.screen.apply { uuid = it.uuid })
            is Replace -> navigator.replace(it.screen.apply { uuid = it.uuid })
        }
    }
}
