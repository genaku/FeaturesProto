package com.example.navrouter_api.navrouter

import com.example.navrouter_api.router.Feature

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