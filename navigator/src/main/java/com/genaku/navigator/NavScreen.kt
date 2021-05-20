package com.genaku.navigator

import com.genaku.navigator_core.EmptyScreenResult
import com.genaku.navigator_core.Screen
import com.genaku.navigator_core.ScreenParams
import com.genaku.navigator_core.ScreenResult
import kotlinx.coroutines.flow.MutableStateFlow

abstract class NavScreen(val destinationResId: Int): Screen {

    abstract override val params: ScreenParams

    override val resultStateFlow: MutableStateFlow<ScreenResult> = MutableStateFlow(
        EmptyScreenResult
    )
}