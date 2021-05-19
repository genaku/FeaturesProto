package com.genaku.navigator

import com.genaku.navigator_core.Feature

abstract class NavFeature(destinationResId: Int): NavScreen(destinationResId), Feature {
    override val isAvailable: Boolean = true
}

