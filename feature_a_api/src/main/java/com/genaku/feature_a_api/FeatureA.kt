package com.genaku.feature_a_api

import com.example.navrouter_api.navrouter.NavFeature
import com.example.navrouter_api.router.ScreenParams
import com.example.navrouter_api.router.ScreenResult


interface FeatureA: NavFeature

class FeatureAParams(val id: Long, val films: List<String>) : ScreenParams

data class FeatureAResult(val success: Boolean) : ScreenResult