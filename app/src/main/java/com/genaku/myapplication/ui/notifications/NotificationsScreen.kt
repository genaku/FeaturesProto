package com.genaku.myapplication.ui.notifications

import com.genaku.myapplication.R
import com.genaku.navrouter.AbstractNavScreen
import com.example.navrouter_api.router.ScreenParams
import com.example.navrouter_api.router.ScreenResult

class NotificationsScreen(override val params: NotificationsScreenParams) :
    AbstractNavScreen(R.id.navigation_notifications)

class NotificationsScreenParams(val message: String) : ScreenParams

class NotificationsScreenResult(val answer: String) : ScreenResult