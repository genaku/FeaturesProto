package com.genaku.navigator

import android.os.Bundle
import android.util.Log
import com.genaku.navigator_core.ScreenArguments
import com.genaku.navigator_core.ScreenResult
import kotlinx.coroutines.flow.Flow
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.NoSuchElementException

open class NavRouterImpl : LocalRouter {

    private val queue = NavCommandQueue()
    private val screens: ConcurrentHashMap<Long, NavScreen> = ConcurrentHashMap()

    override val navCommandFlow: Flow<NavCommand>
        get() = queue.navCommandFlow

    override fun start(screen: NavScreen) {
        val uid = Date().time
        screens[uid] = screen
        queue.send(screen.toNavCommandOpen(uid))
    }

    override fun getUid(arguments: Bundle?): Long? =
        arguments?.getLong(NAV_UID)

    override fun getArguments(uid: Long): ScreenArguments? =
        screens[uid]?.arguments

    override fun finishWithResult(uid: Long, result: ScreenResult) {
        Log.d("TAG", "finish for uid=$uid with result $result")
        val screen = screens[uid]
            ?: throw NoSuchElementException("Screen with uid = $uid not found")
        screen.resultStateFlow.tryEmit(result)
        screens.remove(uid)
        queue.send(Back)
    }

    override fun finish(uid: Long) {
        Log.d("TAG", "finish for uid=$uid")
        screens.remove(uid)
        queue.send(Back)
    }

    private fun NavScreen.toNavCommandOpen(uid: Long): NavCommand =
        Open(destinationResId, Bundle().apply {
            putLong(NAV_UID, uid)
        })

    companion object {
        private const val NAV_UID = "navUid"
    }
}