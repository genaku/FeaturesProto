package com.genaku.router

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.Serializable

/**
 * Screen
 *
 * Represents any screen for navigation
 *
 * @author Gena Kuchergin
 */
interface RouterScreen: Serializable {

    /**
     * Parameters for screen
     */
    val params: ScreenParams

    /**
     * State flow with result returned from the screen on finish
     */
    val resultStateFlow: MutableStateFlow<ScreenResult>

    /**
     * Execute before start of screen
     */
    fun beforeStart() {}

    /**
     * Execute on screen finish
     */
    fun onFinish() {}
}

/**
 * Observe screen result
 *
 * @param coroutineScope - coroutine scope to receive data from result state flow when result is class of [T]
 * @param block - lambda function to process received result
 *
 * @author Gena Kuchergin
 */
inline fun <reified T : ScreenResult> RouterScreen.observe(coroutineScope: CoroutineScope, crossinline block: (T) -> Unit) {
    coroutineScope.launch {
        resultStateFlow.collect {
            if (it is T) {
                block(it)
            }
        }
    }
}