package com.genaku.feature_a_api

import com.genaku.navigator.base.ScreenResult
import com.genaku.navigator.base.ScreenParams
import com.genaku.navigator.nav.NavFeature

interface FeatureA: NavFeature

class FeatureAParams(val id: Long, val films: List<String>) : ScreenParams

data class FeatureAResult(val success: Boolean) : ScreenResult