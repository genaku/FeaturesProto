package com.genaku.navrouter

import com.genaku.router.AbstractRouter
import com.genaku.persistentrouter.PersistentCommandQueue
import com.genaku.persistentrouter.PersistentInstanceState
import com.genaku.persistentrouter.PersistentRouterScreens
import com.genaku.persistentrouter.PersistentRouterState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.util.*

/**
 * Navigation router based on Navigation Component
 *
 * @author Gena Kuchergin
 */
class NavRouter(
    name: String,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    instanceState: PersistentRouterState<NavCommand, NavScreen> = PersistentRouterState(name, dispatcher)
) : AbstractRouter<NavScreen, NavCommand>(instanceState.commandQueue, instanceState.routerScreens),
    PersistentInstanceState by instanceState {

    override fun getStartCommand(screen: NavScreen, uuid: UUID): NavCommand =
        Open(screen.destinationResId, uuid)

    override fun getFinishCommand(uuid: UUID): NavCommand = Back
}