package com.genaku.router

abstract class AbstractFeatureRouter<F : Feature, C : RouterCommand>(
    commandQueue: CommandQueue<C>,
    routerScreens: IRouterScreens<F>
) : AbstractRouter<F, C>(commandQueue, routerScreens) {

    override fun start(screen: F) {
        if (screen.isAvailable) {
            super.start(screen)
        }
    }
}