package com.genaku.alligatorrouter

import com.genaku.router.EmptyScreenResult
import com.genaku.router.ScreenParams
import com.genaku.router.ScreenResult
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*

abstract class AbstractAlgScreen(override var uuid: UUID? = null) : AlgScreen {

    abstract override val params: com.genaku.router.ScreenParams

    override val resultStateFlow: MutableStateFlow<com.genaku.router.ScreenResult> = MutableStateFlow(
        com.genaku.router.EmptyScreenResult
    )
}