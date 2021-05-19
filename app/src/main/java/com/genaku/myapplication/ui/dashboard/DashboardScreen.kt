package com.genaku.myapplication.ui.dashboard

import com.genaku.myapplication.R
import com.genaku.navigator.NavScreen
import com.genaku.navigator_core.ScreenArguments
import com.genaku.navigator_core.ScreenResult

class DashboardScreen(override val arguments: DashboardScreenArguments) : NavScreen(R.id.navigation_dashboard)

data class DashboardScreenArguments(val name: String, val id: Int) :
    ScreenArguments

class DashboardScreenResult(val success: Boolean) : ScreenResult