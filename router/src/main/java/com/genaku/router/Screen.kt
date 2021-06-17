package com.genaku.router

import kotlinx.coroutines.flow.MutableStateFlow
import java.io.Serializable

/**
 * Screen
 *
 * Represents any screen for navigation
 *
 * @author Gena Kuchergin
 */
interface Screen: Serializable {

    /**
     * Parameters for screen
     */
    val params: ScreenParams

    /**
     * State flow with result returned from the screen on finish
     */
    fun getResultStateFlow(): MutableStateFlow<ScreenResult>
}