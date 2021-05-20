package com.genaku.navigator_core

import kotlinx.coroutines.flow.MutableStateFlow

interface Screen {
    val params: ScreenParams
    val resultStateFlow: MutableStateFlow<ScreenResult>
}