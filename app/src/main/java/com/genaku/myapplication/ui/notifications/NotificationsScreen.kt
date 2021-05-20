package com.genaku.myapplication.ui.notifications

import com.genaku.myapplication.R
import com.genaku.navigator.nav.AbstractNavScreen
import com.genaku.navigator.base.ScreenParams
import com.genaku.navigator.base.ScreenResult

class NotificationsScreen(override val params: NotificationsScreenParams) :
    AbstractNavScreen(R.id.navigation_notifications)

class NotificationsScreenParams(val message: String) : ScreenParams

class NotificationsScreenResult(val answer: String) : ScreenResult