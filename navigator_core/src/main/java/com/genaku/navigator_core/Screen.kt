package com.genaku.navigator_core

import kotlinx.coroutines.flow.MutableStateFlow

interface Screen {
    val arguments: ScreenArguments
    val resultStateFlow: MutableStateFlow<ScreenResult>
}