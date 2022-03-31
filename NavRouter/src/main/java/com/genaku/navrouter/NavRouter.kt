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
 * Navigation router based on Navigation Component
 *
 * @author Gena Kuchergin
 */
class NavRouter(
    commandsKey: String = PersistentCommandQueue.DEFAULT_KEY,
    screensKey: String = PersistentRouterScreens.DEFAULT_KEY,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    instanceState: PersistentRouterState<NavCommand, NavScreen> = PersistentRouterState(
        commandsKey,
        screensKey,
        dispatcher
    )
) : AbstractRouter<NavScreen, NavCommand>(instanceState.commandQueue, instanceState.routerScreens),
    PersistentInstanceState by instanceState {

    override fun getStartCommand(screen: NavScreen, uuid: UUID): NavCommand =
        Open(screen.destinationResId, uuid)

    override fun getFinishCommand(uuid: UUID): NavCommand = Back
}