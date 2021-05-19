package com.genaku.myapplication.ui.notifications

import com.genaku.myapplication.R
import com.genaku.navigator.NavScreen
import com.genaku.navigator_core.ScreenArguments
import com.genaku.navigator_core.ScreenResult

class NotificationsScreen(override val arguments: NotificationsScreenArguments) :
    NavScreen(R.id.navigation_notifications)

class NotificationsScreenArguments(val name: String) :
    ScreenArguments

class NotificationsScreenResult(val success: Boolean) :
    ScreenResult