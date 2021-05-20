package com.genaku.navigator

class FeatureRouter(navCommandQueue: NavCommandQueue) :
    NavRouterImpl<NavFeature>(navCommandQueue) {

    override fun start(screen: NavFeature) {
        if (screen.isAvailable) {
            super.start(screen)
        }
    }

    override fun close(uid: Long) {
        val screen =
            screens[uid] ?: throw NoSuchElementException("Screen with uid = $uid not found")
        navCommandQueue.send(Action(screen.finishActionResId))
    }
}