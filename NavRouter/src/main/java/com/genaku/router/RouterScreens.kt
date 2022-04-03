package com.genaku.router

import com.example.navrouter_api.router.RouterScreen
import com.example.navrouter_api.router.ScreenParameters
import java.util.*

interface RouterScreens<S : RouterScreen> : ScreenParameters {
    fun addScreen(screen: S): UUID
    fun deleteScreen(uuid: UUID)
    fun getScreenOrNull(uuid: UUID): S?
    fun getAllScreens(): Map<UUID, S>
    fun addScreens(newScreens: Map<UUID, S>)
}