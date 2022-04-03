package com.example.navrouter_api.navrouter

import com.example.navrouter_api.router.RouterScreen
import java.io.Serializable

/**
 * Screen to use in Navigation Component router
 *
 * @author Gena Kuchergin
 */
interface NavScreen : RouterScreen, Serializable {

    /**
     * ResId of screen start destination in nav graph
     */
    val destinationResId: Int
}