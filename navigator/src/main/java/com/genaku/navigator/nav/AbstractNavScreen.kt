package com.genaku.navigator.nav

import com.genaku.navigator.base.EmptyScreenResult
import com.genaku.navigator.base.ScreenResult
import com.genaku.navigator.base.ScreenParams
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Abstract nav screen
 *
 * @property destinationResId - ResId of screen start destination in nav graph
 *
 * @author Gena Kuchergin
 */
abstract class AbstractNavScreen(override val destinationResId: Int): NavScreen {

    abstract override val params: ScreenParams

    override val resultStateFlow: MutableStateFlow<ScreenResult> = MutableStateFlow(
        EmptyScreenResult
    )
}