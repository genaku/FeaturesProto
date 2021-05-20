package com.genaku.navigator

import com.genaku.navigator.nav.*

class FeatureRouterImpl(navCommandQueue: NavCommandQueue) : NavRouterImpl<NavFeature>(navCommandQueue), FeatureRouter {

    override fun start(screen: NavFeature) {
        if (screen.isAvailable) {
            super.start(screen)
        }
    }

    override fun close(uid: Long) {
        val screen =
            screens[uid] ?: throw NoSuchElementException("Screen with uid = $uid not found")
        navCommandQueue.send(BackAction(screen.finishActionResId))
    }
}