package com.genaku.feature_b_ui

import com.genaku.navrouter.AbstractNavScreen
import com.example.navrouter_api.router.ScreenParams
import com.example.navrouter_api.router.ScreenResult

class FeatureBEndScreen(override val params: FeatureBEndScreenParams) : AbstractNavScreen(R.id.featureBEndFragment)

class FeatureBEndScreenParams(val message: String) : ScreenParams

class FeatureBEndScreenResult(val answer: String) : ScreenResult