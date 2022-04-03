package com.genaku.myapplication.ui.dashboard

import com.genaku.myapplication.R
import com.genaku.navrouter.AbstractNavScreen
import com.example.navrouter_api.router.ScreenParams
import com.example.navrouter_api.router.ScreenResult

class DashboardScreen(override val params: DashboardScreenParams) : AbstractNavScreen(R.id.navigation_dashboard)

data class DashboardScreenParams(val name: String, val id: Int) : ScreenParams

class DashboardScreenResult(val success: Boolean) : ScreenResult