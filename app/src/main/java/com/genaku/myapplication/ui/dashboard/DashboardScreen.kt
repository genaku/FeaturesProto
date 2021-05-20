package com.genaku.myapplication.ui.dashboard

import com.genaku.myapplication.R
import com.genaku.navigator.NavScreen
import com.genaku.navigator_core.ScreenParams
import com.genaku.navigator_core.ScreenResult

class DashboardScreen(override val params: DashboardScreenParams) :
    NavScreen(R.id.navigation_dashboard)

data class DashboardScreenParams(val name: String, val id: Int) : ScreenParams

class DashboardScreenResult(val success: Boolean) : ScreenResult