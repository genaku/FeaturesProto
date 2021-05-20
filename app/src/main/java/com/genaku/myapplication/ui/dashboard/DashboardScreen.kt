package com.genaku.myapplication.ui.dashboard

import com.genaku.myapplication.R
import com.genaku.navigator.nav.AbstractNavScreen
import com.genaku.navigator.base.ScreenParams
import com.genaku.navigator.base.ScreenResult

class DashboardScreen(override val params: DashboardScreenParams) : AbstractNavScreen(R.id.navigation_dashboard)

data class DashboardScreenParams(val name: String, val id: Int) : ScreenParams

class DashboardScreenResult(val success: Boolean) : ScreenResult