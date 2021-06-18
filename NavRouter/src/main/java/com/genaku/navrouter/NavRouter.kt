package com.genaku.navrouter

import com.genaku.router.AbstractRouter
import com.genaku.router.CommandQueue
import com.genaku.router.IRouterScreens

/**
 * Navigation router based on Navigation Component
 *
 * @author Gena Kuchergin
 */
open class NavRouter(
    commandQueue: CommandQueue<NavCommand>,
    routerScreens: IRouterScreens<NavScreen>
) : AbstractRouter<NavScreen, NavCommand>(commandQueue, routerScreens) {

    override fun getStartCommand(screen: NavScreen, uid: Long): NavCommand =
        Open(screen.destinationResId, uid)

    override fun getFinishCommand(uid: Long): NavCommand = Back

}