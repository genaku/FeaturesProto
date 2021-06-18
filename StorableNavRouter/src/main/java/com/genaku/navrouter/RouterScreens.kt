package com.genaku.navrouter

import android.os.Bundle
import android.util.Log
import com.genaku.router.IRouterScreens
import com.genaku.router.Screen
import com.genaku.router.ScreenParams
import java.io.Serializable
import java.util.*
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

class RouterScreens<S : Screen>(private val key: String = DEFAULT_KEY) : IRouterScreens<S>, StorableInstanceState {

    private val lock = ReentrantReadWriteLock()
    private val screens: MutableMap<Long, S> = mutableMapOf()

    override fun addScreen(screen: S): Long {
        val uid = Date().time
        lock.write {
            screens[uid] = screen
        }
        return uid
    }

    override fun deleteScreen(uid: Long) {
        lock.write {
            screens.remove(uid)
        }
    }

    override fun getScreenOrNull(uid: Long): S? = lock.read {
        screens[uid]
    }

    override fun getAllScreens() = lock.read { screens }

    override fun setScreens(newScreens: Map<Long, S>) = lock.write {
        screens.putAll(newScreens)
    }

    override fun getParametersOrNull(uid: Long): ScreenParams? = lock.write {
        screens[uid]?.params
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val data: ScreensData =
            savedInstanceState.getSerializable(key) as RouterScreens<S>.ScreensData? ?: return
        Log.d("TAF", "restore ${data.screens}")
        setScreens(data.screens)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val data = ScreensData(
            getAllScreens()
        )
        outState.putSerializable(key, data)
        Log.d("TAF", "save ${data.screens}")
    }

    inner class ScreensData(val screens: Map<Long, S>) : Serializable

    companion object {
        private const val DEFAULT_KEY = "RouterScreens"
    }
}