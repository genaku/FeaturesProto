package com.genaku.navrouter

import com.example.navrouter_api.navrouter.NavScreen
import com.genaku.persistentrouter.PersistentInstanceState
import com.genaku.persistentrouter.PersistentRouterState
import com.genaku.router.AbstractRouter
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
    instanceState: PersistentRouterState<NavScreen, NavCommand> = PersistentRouterState(name, dispatcher)
) : AbstractRouter<NavScreen, NavCommand>(instanceState), PersistentInstanceState by instanceState {

    override fun getStartCommand(screen: NavScreen, uuid: UUID): NavCommand =
        Open(screen.destinationResId, uuid)

    override fun getFinishCommand(uuid: UUID): NavCommand = Back
}