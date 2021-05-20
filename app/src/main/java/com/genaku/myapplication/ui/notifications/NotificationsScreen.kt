package com.genaku.myapplication.ui.notifications

import com.genaku.myapplication.R
import com.genaku.navigator.NavScreen
import com.genaku.navigator_core.ScreenParams
import com.genaku.navigator_core.ScreenResult

class NotificationsScreen(override val params: NotificationsScreenParams) :
    NavScreen(R.id.navigation_notifications)

class NotificationsScreenParams(val message: String) : ScreenParams

class NotificationsScreenResult(val answer: String) : ScreenResult