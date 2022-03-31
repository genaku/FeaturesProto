package com.genaku.feature_alligator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.genaku.alligatorrouter.AlgCommand
import com.genaku.alligatorrouter.AlgFeature
import com.genaku.alligatorrouter.AlgScreen
import com.genaku.router.PersistentCommandQueue
import com.genaku.router.PersistentRouterScreens
import kotlinx.coroutines.Job
import me.aartikov.alligator.AndroidNavigator
import me.aartikov.alligator.NavigationContext
import me.aartikov.alligator.Screen
import me.aartikov.alligator.ScreenResult
import me.aartikov.alligator.listeners.ScreenResultListener
import org.koin.android.ext.android.inject

class AlgMainActivity : AppCompatActivity(R.layout.activity_main), ScreenResultListener {

    private val commandQueue: PersistentCommandQueue<AlgCommand> by inject()
    private val routerScreens: PersistentRouterScreens<AlgScreen> by inject()
    private val featureScreens: PersistentRouterScreens<AlgFeature> by inject()
    private val navigator: AndroidNavigator by inject()

    private val fragmentContainerId: Int = R.id.host_fragment

    private var navJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAF", "create ${this.javaClass.simpleName}")
        navJob = commandQueue.connectTo(lifecycleScope, navigator)
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

    override fun onResumeFragments() {
        super.onResumeFragments()
        bindNavigator()
    }

    override fun onPause() {
        navJob?.cancel()
        navigator.unbind(this)
        super.onPause()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.run {
            navigator.activityResultHandler.onNewIntent(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        navigator.activityResultHandler.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun bindNavigator() {
        val navigationContextBuilder = NavigationContext.Builder(this, navigator.navigationFactory)
            .screenResultListener(this)
//            .transitionAnimationProvider(transitionAnimationProvider)
        if (fragmentContainerId > 0) {
            navigationContextBuilder.fragmentNavigation(supportFragmentManager, fragmentContainerId)
        }
        navigator.bind(navigationContextBuilder.build())
    }

    override fun onScreenResult(screenClass: Class<out Screen>?, result: ScreenResult?) {
//        TODO("Not yet implemented")
    }
}