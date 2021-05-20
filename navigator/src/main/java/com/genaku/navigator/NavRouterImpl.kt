package com.genaku.navigator

import android.os.Bundle
import android.util.Log
import com.genaku.navigator_core.ScreenParams
import com.genaku.navigator_core.ScreenResult
import kotlinx.coroutines.flow.Flow
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.NoSuchElementException

open class NavRouterImpl<T: NavScreen>(protected val navCommandQueue: NavCommandQueue) : NavRouter<T> {

    protected val screens: ConcurrentHashMap<Long, T> = ConcurrentHashMap()

    override val navCommandFlow: Flow<NavCommand>
        get() = navCommandQueue.navCommandFlow

    override fun start(screen: T) {
        val uid = Date().time
        Log.d("TAF", "start ${screen.javaClass.simpleName} with uid=$uid")
        screens[uid] = screen
        navCommandQueue.send(screen.toNavCommandOpen(uid))
    }

    override fun getUid(arguments: Bundle?): Long? =
        arguments?.getLong(NAV_UID)

    override fun getArguments(uid: Long): ScreenParams? =
        screens[uid]?.params

    override fun finishWithResult(uid: Long, result: ScreenResult) {
        Log.d("TAF", "finish for uid=$uid with result $result")
        val screen = screens[uid]
            ?: throw NoSuchElementException("Screen with uid = $uid not found")
        screen.resultStateFlow.tryEmit(result)
        close(uid)
        screens.remove(uid)
    }

    override fun finish(uid: Long) {
        Log.d("TAF", "finish for uid=$uid")
        close(uid)
        screens.remove(uid)
    }

    protected open fun close(uid: Long) {
        navCommandQueue.send(Back)
    }

    private fun NavScreen.toNavCommandOpen(uid: Long): NavCommand =
        Open(destinationResId, Bundle().apply {
            putLong(NAV_UID, uid)
        })

    companion object {
        private const val NAV_UID = "navUid"
    }
}