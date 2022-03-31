package com.genaku.navrouter

import com.genaku.router.AbstractNavRouter
import com.genaku.router.CommandQueue
import com.genaku.router.RouterScreens
import java.util.*

/**
 * Feature router interface
 *
 * @author Gena Kuchergin
 */
open class NavFeatureRouter(
    commandQueue: CommandQueue<NavCommand>,
    routerScreens: RouterScreens<NavFeature>
) : AbstractNavRouter<NavFeature>(commandQueue, routerScreens) {

    override fun getStartCommand(screen: NavFeature, uuid: UUID): NavCommand =
        Open(screen.destinationResId, uuid)

    override fun getFinishCommand(uuid: UUID): NavCommand {
        val screen = routerScreens.getScreenOrNull(uuid)
            ?: throw NoSuchElementException("Screen with uid = $uuid not found")
        return BackAction(screen.finishActionResId)
    }

    override fun start(screen: NavFeature) {
        if (screen.isAvailable) {
            super.start(screen)
        }
    }
}