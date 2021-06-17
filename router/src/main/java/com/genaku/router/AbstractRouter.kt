package com.genaku.router

import kotlinx.coroutines.flow.Flow
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

abstract class AbstractRouter<S : Screen, C : RouterCommand>(
    protected val commandQueue: StorableCommandQueue<C>
) : Router<S>, CommandFlow<C> {

    private val lock = ReentrantReadWriteLock()
    private val screens: MutableMap<Long, S> = mutableMapOf()

    abstract fun getStartCommand(screen: S, uid: Long): C

    abstract fun getFinishCommand(uid: Long): C

    override val commandFlow: Flow<C>
        get() = commandQueue.commandFlow

    protected fun getScreenOrNull(uid: Long): S? = lock.read {
        screens[uid]
    }

    protected fun getAllScreens() = lock.read { screens }

    protected fun setScreens(newScreens: Map<Long, S>) = lock.write {
        screens.putAll(newScreens)
    }

    override fun start(screen: S) {
        val uid = Date().time
        lock.write {
            screens[uid] = screen
        }
        commandQueue.send(getStartCommand(screen, uid))
    }

    override fun finish(uid: Long) {
        commandQueue.send(getFinishCommand(uid))
        lock.write {
            screens.remove(uid)
        }
    }

    override fun finishWithResult(uid: Long, result: ScreenResult) {
        val screen = getScreenOrNull(uid)
            ?: throw NoSuchElementException("Screen with uid = $uid not found")
        screen.getResultStateFlow().tryEmit(result)
        finish(uid)
    }

    override fun getParametersOrNull(uid: Long): ScreenParams? = lock.write {
        screens[uid]?.params
    }
}