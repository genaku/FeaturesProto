package com.genaku.navigator.base

/**
 * Feature
 *
 * Represents Feature (independent feature module) for navigation (start and finish feature)
 *
 * @author Gena Kuchergin
 */
interface Feature: Screen {

    /**
     * Is feature available (can be started?)
     */
    val isAvailable: Boolean
}