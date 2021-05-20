package com.genaku.feature_b_ui

import com.genaku.navigator.base.ScreenResult
import com.genaku.navigator.base.ScreenParams
import com.genaku.navigator.nav.AbstractNavScreen

class FeatureBEndScreen(override val params: FeatureBEndScreenParams) :
    AbstractNavScreen(R.id.featureBEndFragment)

class FeatureBEndScreenParams(val message: String) : ScreenParams

class FeatureBEndScreenResult(val answer: String) : ScreenResult