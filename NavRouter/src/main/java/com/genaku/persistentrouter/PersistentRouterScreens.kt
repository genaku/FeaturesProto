package com.genaku.persistentrouter

import android.os.Bundle
import android.util.Log
import com.genaku.router.RouterScreen
import java.io.Serializable
import java.util.*

class PersistentRouterScreens<S : RouterScreen>(name: String) : BaseRouterScreens<S>(), PersistentInstanceState {

    private val key = PRE_KEY + name

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val data = savedInstanceState.getSerializable(key) as? ScreensData<S>
        if (data == null) {
            addScreens(emptyMap())
        } else {
            log("restore ${data.screens}")
            addScreens(data.screens)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val data = ScreensData(
            getAllScreens()
        )
        log("save ${data.screens}")
        if (data.screens.isNotEmpty()) {
            outState.putSerializable(key, data)
        }
    }

    private fun log(msg: String) {
        Log.d("TAF", "[${Thread.currentThread()}] $msg")
    }

    class ScreensData<S>(val screens: Map<UUID, S>) : Serializable

    companion object {
        private const val PRE_KEY = "RouterScreens"
    }
}