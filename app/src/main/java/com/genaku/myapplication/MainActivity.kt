package com.genaku.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.genaku.navrouter.*
import com.genaku.router.CommandFlow
import com.genaku.router.RouterCommand
import com.genaku.navrouter.PersistentNavFeatureRouter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity(R.layout.activity_main), KoinScopeComponent {

    override val scope: Scope = AppScope.scope

    private val featureRouter: PersistentNavFeatureRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        featureRouter.connect(this, findNavController(R.id.rootNavHostFragment))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        featureRouter.onRestoreInstanceState(savedInstanceState)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        featureRouter.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }
}

fun <T : RouterCommand> CommandFlow<T>.connect(
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
