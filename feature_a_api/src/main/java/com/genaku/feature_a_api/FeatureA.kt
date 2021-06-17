package com.genaku.feature_a_api

import com.genaku.navrouter.NavFeature
import com.genaku.router.ScreenParams
import com.genaku.router.ScreenResult

interface FeatureA: NavFeature

class FeatureAParams(val id: Long, val films: List<String>) : ScreenParams

data class FeatureAResult(val success: Boolean) : ScreenResult