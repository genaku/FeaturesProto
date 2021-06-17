package com.genaku.navrouter

import com.genaku.router.AbstractFeatureRouter
import com.genaku.router.StorableCommandQueue

/**
 * Feature router interface
 *
 * @author Gena Kuchergin
 */
class NavFeatureRouter(
    commandQueue: StorableCommandQueue<NavCommand>
) : AbstractFeatureRouter<NavFeature, NavCommand>(commandQueue) {

    override fun getStartCommand(screen: NavFeature, uid: Long): NavCommand =
        Open(screen.destinationResId, uid)

    override fun getFinishCommand(uid: Long): NavCommand {
        val screen =
            getScreenOrNull(uid) ?: throw NoSuchElementException("Screen with uid = $uid not found")
        return BackAction(screen.finishActionResId)
    }
}