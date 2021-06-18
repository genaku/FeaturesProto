package com.genaku.router

import kotlinx.coroutines.flow.Flow
import java.util.*

abstract class AbstractRouter<S : Screen, C : RouterCommand>(
    protected val commandQueue: CommandQueue<C>,
    protected val routerScreens: IRouterScreens<S>
) : Router<S>, CommandFlow<C> {

    abstract fun getStartCommand(screen: S, uid: Long): C

    abstract fun getFinishCommand(uid: Long): C

    override val commandFlow: Flow<C>
        get() = commandQueue.commandFlow

    override fun start(screen: S) {
        val uid = routerScreens.addScreen(screen)
        commandQueue.send(getStartCommand(screen, uid))
    }

    override fun finish(uid: Long) {
        commandQueue.send(getFinishCommand(uid))
        routerScreens.deleteScreen(uid)
    }

    override fun finishWithResult(uid: Long, result: ScreenResult) {
        val screen = routerScreens.getScreenOrNull(uid)
            ?: throw NoSuchElementException("Screen with uid = $uid not found")
        screen.getResultStateFlow().tryEmit(result)
        finish(uid)
    }

    override fun getParametersOrNull(uid: Long): ScreenParams? =
        routerScreens.getParametersOrNull(uid)
}