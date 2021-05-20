package com.genaku.navigator

import com.genaku.navigator_core.Feature

abstract class NavFeature(destinationResId: Int, val finishActionResId: Int) :
    NavScreen(destinationResId), Feature {

    override val isAvailable: Boolean = true
}

