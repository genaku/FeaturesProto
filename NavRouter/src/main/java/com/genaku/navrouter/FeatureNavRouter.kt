package com.genaku.navrouter

import com.genaku.router.AbstractRouter
import com.genaku.storablerouter.PersistentCommandQueue
import com.genaku.storablerouter.PersistentInstanceState
import com.genaku.storablerouter.PersistentRouterScreens
import com.genaku.storablerouter.PersistentRouterState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.util.*

/**
 * Feature router interface
 *
 * @author Gena Kuchergin
 */
class FeatureNavRouter(
    commandsKey: String = PersistentCommandQueue.DEFAULT_KEY,
    screensKey: String = PersistentRouterScreens.DEFAULT_KEY,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    instanceState: PersistentRouterState<NavCommand, NavFeature> = PersistentRouterState(
        commandsKey,
        screensKey,
        dispatcher
    )
) : AbstractRouter<NavFeature, NavCommand>(instanceState.commandQueue, instanceState.routerScreens),
    PersistentInstanceState by instanceState {

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