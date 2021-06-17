package com.genaku.navrouter

import com.genaku.router.AbstractRouter
import com.genaku.router.BaseStorableCommandQueue
import com.genaku.router.StorableCommandQueue

/**
 * Navigation router based on Navigation Component
 *
 * @author Gena Kuchergin
 */
open class NavRouter(commandQueue: StorableCommandQueue<NavCommand> = BaseStorableCommandQueue()) :
    AbstractRouter<NavScreen, NavCommand>(commandQueue) {

    override fun getStartCommand(screen: NavScreen, uid: Long): NavCommand =
        Open(screen.destinationResId, uid)

    override fun getFinishCommand(uid: Long): NavCommand = Back

}