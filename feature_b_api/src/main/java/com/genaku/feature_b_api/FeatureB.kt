package com.genaku.feature_b_api

import com.example.navrouter_api.navrouter.NavFeature
import com.example.navrouter_api.router.ScreenParams
import com.example.navrouter_api.router.ScreenResult

interface FeatureB : NavFeature {
    val repo: Repppo
}

class FeatureBParams(needReset: Boolean) : ScreenParams

data class FeatureBResult(val name: String) : ScreenResult

class Repppo