package com.genaku.navrouter

import com.example.navrouter_api.navrouter.NavScreen
import com.example.navrouter_api.router.EmptyScreenResult
import com.example.navrouter_api.router.ScreenParams
import com.example.navrouter_api.router.ScreenResult
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

    @Transient
    override val resultStateFlow: MutableStateFlow<ScreenResult> = MutableStateFlow(
        EmptyScreenResult
    )
}