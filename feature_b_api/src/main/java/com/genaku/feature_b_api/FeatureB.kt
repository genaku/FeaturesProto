package com.genaku.feature_b_api

import com.genaku.navrouter.NavFeature
import com.genaku.router.ScreenParams
import com.genaku.router.ScreenResult

interface FeatureB : NavFeature {
    val repo: Repppo
}

class FeatureBParams(needReset: Boolean) : ScreenParams

data class FeatureBResult(val name: String) : ScreenResult

class Repppo