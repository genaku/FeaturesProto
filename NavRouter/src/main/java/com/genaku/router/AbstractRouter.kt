package com.genaku.router

import android.util.Log
import com.example.navrouter_api.router.Router
import com.example.navrouter_api.router.RouterScreen
import com.example.navrouter_api.router.ScreenParameters
import com.example.navrouter_api.router.ScreenResult
import java.util.*

abstract class AbstractRouter<S : RouterScreen, C : RouterCommand>(
    protected val routerState: RouterState<S, C>,
) : Router<S>, CommandFlow<C> by routerState.commandQueue, ScreenParameters by routerState.routerScreens {

    abstract fun getStartCommand(screen: S, uuid: UUID): C

    abstract fun getFinishCommand(uuid: UUID): C

    protected val routerScreens = routerState.routerScreens
    protected val commandQueue = routerState.commandQueue

    override fun start(screen: S) {
        screen.beforeStart()
        val uuid = routerScreens.addScreen(screen)
        Log.d("TAF", "$this start $uuid")
        commandQueue.send(getStartCommand(screen, uuid))
    }

    override fun finish(uuid: UUID) {
        Log.d("TAF", "$this finish $uuid")
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

