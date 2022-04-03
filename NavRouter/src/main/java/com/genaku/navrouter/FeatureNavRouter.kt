package com.genaku.navrouter

import com.example.navrouter_api.navrouter.FeatureNavRouter
import com.example.navrouter_api.navrouter.NavFeature
import com.example.navrouter_api.router.FeatureRouter
import com.genaku.persistentrouter.PersistentInstanceState
import com.genaku.persistentrouter.PersistentRouterState
import com.genaku.router.AbstractRouter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.util.*

/**
 * Feature router interface
 *
 * @author Gena Kuchergin
 */
class FeatureNavRouterImpl(
    name: String,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    instanceState: PersistentRouterState<NavFeature, NavCommand> = PersistentRouterState(name, dispatcher)
) : AbstractRouter<NavFeature, NavCommand>(instanceState), PersistentInstanceState by instanceState, FeatureNavRouter {

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