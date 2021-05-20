package com.genaku.navigator.nav

import com.genaku.navigator.base.Feature

/**
 * Feature navigation info to use in Navigation Component router
 *
 * @author Gena Kuchergin
 */
interface NavFeature: NavScreen, Feature {

    /**
     * ResId of action to finish feature nav graph
     */
    val finishActionResId: Int
}