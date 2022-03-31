package com.genaku.feature_alligator.notifications

import com.genaku.alligatorrouter.AbstractAlgScreen
import com.genaku.router.ScreenParams
import com.genaku.router.ScreenResult

class NotificationsScreen(override val params: NotificationsScreenParams) : AbstractAlgScreen()

class NotificationsScreenParams(val message: String) : ScreenParams

class NotificationsScreenResult(val answer: String) : ScreenResult