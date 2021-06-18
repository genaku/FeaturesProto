package com.genaku.navrouter

import com.genaku.router.AbstractFeatureRouter
import com.genaku.router.CommandQueue
import com.genaku.router.IRouterScreens

/**
 * Feature router interface
 *
 * @author Gena Kuchergin
 */
open class NavFeatureRouter(
    commandQueue: CommandQueue<NavCommand>,
    routerScreens: IRouterScreens<NavFeature>
) : AbstractFeatureRouter<NavFeature, NavCommand>(commandQueue, routerScreens) {

    override fun getStartCommand(screen: NavFeature, uid: Long): NavCommand =
        Open(screen.destinationResId, uid)

    override fun getFinishCommand(uid: Long): NavCommand {
        val screen = routerScreens.getScreenOrNull(uid)
            ?: throw NoSuchElementException("Screen with uid = $uid not found")
        return BackAction(screen.finishActionResId)
    }
}