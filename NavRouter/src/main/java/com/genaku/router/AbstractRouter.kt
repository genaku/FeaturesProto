package com.genaku.router

import java.util.*

abstract class AbstractRouter<S : RouterScreen, C : RouterCommand>(
    protected val commandQueue: CommandQueue<C>,
    protected val routerScreens: RouterScreens<S>
) : Router<S>, CommandFlow<C> by commandQueue, ScreenParameters by routerScreens {

    abstract fun getStartCommand(screen: S, uuid: UUID): C

    abstract fun getFinishCommand(uuid: UUID): C

    override fun start(screen: S) {
        screen.beforeStart()
        val uuid = routerScreens.addScreen(screen)
        commandQueue.send(getStartCommand(screen, uuid))
    }

    override fun finish(uuid: UUID) {
        val screen = getScreen(uuid)
        commandQueue.send(getFinishCommand(uuid))
        screen.onFinish()
        routerScreens.deleteScreen(uuid)
    }

    override fun finishWithResult(uuid: UUID, result: ScreenResult) {
        val screen = getScreen(uuid)
        screen.resultStateFlow.tryEmit(result)
        finish(uuid)
    }

    private fun getScreen(uuid: UUID) = (routerScreens.getScreenOrNull(uuid)
        ?: throw NoSuchElementException("Screen with uuid = $uuid not found"))
}