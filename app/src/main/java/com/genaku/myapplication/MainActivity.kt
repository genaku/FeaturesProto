package com.genaku.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.genaku.navrouter.*
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity(R.layout.activity_main), KoinScopeComponent {

    override val scope: Scope = AppScope.scope

    private val commandQueue: BaseStorableCommandQueue<NavCommand> by inject()
    private val routerScreens: RouterScreens<NavScreen> by inject()
    private val featureScreens: RouterScreens<NavFeature> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commandQueue.connectTo(lifecycleScope, findNavController(R.id.rootNavHostFragment))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        routerScreens.onRestoreInstanceState(savedInstanceState)
        featureScreens.onRestoreInstanceState(savedInstanceState)
        commandQueue.onRestoreInstanceState(savedInstanceState)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        commandQueue.onSaveInstanceState(outState)
        featureScreens.onSaveInstanceState(outState)
        routerScreens.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }
}