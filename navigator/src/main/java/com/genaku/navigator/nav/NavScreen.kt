package com.genaku.navigator.nav

import com.genaku.navigator.base.Screen

/**
 * Screen to use in Navigation Component router
 *
 * @author Gena Kuchergin
 */
interface NavScreen : Screen {

    /**
     * ResId of screen start destination in nav graph
     */
    val destinationResId: Int
}