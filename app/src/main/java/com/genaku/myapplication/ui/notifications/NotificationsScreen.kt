package com.genaku.myapplication.ui.notifications

import com.genaku.myapplication.R
import com.genaku.navrouter.AbstractNavScreen
import com.genaku.router.ScreenParams
import com.genaku.router.ScreenResult

class NotificationsScreen(override val params: NotificationsScreenParams) :
    AbstractNavScreen(R.id.navigation_notifications)

class NotificationsScreenParams(val message: String) : ScreenParams

class NotificationsScreenResult(val answer: String) : ScreenResult