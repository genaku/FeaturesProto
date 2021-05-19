package com.genaku.navigator

class FeatureRouter(private val router: NavRouter<NavFeature>): NavRouter<NavFeature> by router {

    override fun start(screen: NavFeature) {
        if (screen.isAvailable) {
            router.start(screen)
        }
    }
}