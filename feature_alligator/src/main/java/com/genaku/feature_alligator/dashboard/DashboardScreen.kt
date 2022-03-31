package com.genaku.feature_alligator.dashboard

import com.genaku.alligatorrouter.AbstractAlgScreen
import com.genaku.router.ScreenParams
import com.genaku.router.ScreenResult

class DashboardScreen(override val params: DashboardScreenParams) : AbstractAlgScreen()

data class DashboardScreenParams(val name: String, val id: Int) : ScreenParams

class DashboardScreenResult(val success: Boolean) : ScreenResult