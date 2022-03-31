package com.genaku.alligatorrouter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.genaku.router.AbstractRouter
import com.genaku.router.CommandQueue
import com.genaku.router.RouterScreens
import me.aartikov.alligator.AndroidNavigator
import me.aartikov.alligator.NavigationContext
import me.aartikov.alligator.animations.providers.TransitionAnimationProvider
import me.aartikov.alligator.listeners.ScreenResultListener
import java.util.*

open class AlgRouter(
    commandQueue: com.genaku.router.CommandQueue<AlgCommand>,
    screens: com.genaku.router.RouterScreens<AlgScreen>,
    protected open val navigator: AndroidNavigator
) : com.genaku.router.AbstractRouter<AlgScreen, AlgCommand>(commandQueue, screens), AlligatorRouter {

    override fun getStartCommand(screen: AlgScreen, uuid: UUID): AlgCommand = Open(screen, uuid)

    override fun getFinishCommand(uuid: UUID): AlgCommand = Back

    override fun bind(
        activity: AppCompatActivity,
        screenResultListener: ScreenResultListener,
        fragmentContainerId: Int,
        transitionAnimationProvider: TransitionAnimationProvider?
    ) {
        val navigationContextBuilder = NavigationContext.Builder(
            activity,
            navigator.navigationFactory
        )
            .screenResultListener(screenResultListener)
            .transitionAnimationProvider(transitionAnimationProvider)
        if (fragmentContainerId > 0) {
            navigationContextBuilder.fragmentNavigation(
                activity.supportFragmentManager,
                fragmentContainerId
            )
        }
        navigator.bind(navigationContextBuilder.build())
    }

    override fun unbind(activity: AppCompatActivity) {
        navigator.unbind(activity)
    }

    override fun onNewIntent(intent: Intent?) {
        intent ?: return
        navigator.activityResultHandler.onNewIntent(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        navigator.activityResultHandler.onActivityResult(requestCode, resultCode, data)
    }

    override fun getUUID(activity: AppCompatActivity): UUID =
        navigator.screenResolver.getScreen<AlgScreen>(activity).uuid!!

    override fun getUUID(fragment: Fragment): UUID =
        navigator.screenResolver.getScreen<AlgScreen>(fragment).uuid!!
}