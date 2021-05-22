package com.genaku.feature_b_ui

import com.genaku.navrouterbase.AbstractNavScreen
import com.genaku.router.ScreenParams
import com.genaku.router.ScreenResult

class FeatureBEndScreen(override val params: FeatureBEndScreenParams) :
    AbstractNavScreen(R.id.featureBEndFragment)

class FeatureBEndScreenParams(val message: String) : ScreenParams

class FeatureBEndScreenResult(val answer: String) : ScreenResult