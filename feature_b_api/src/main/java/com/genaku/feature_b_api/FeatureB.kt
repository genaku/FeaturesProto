package com.genaku.feature_b_api

import com.genaku.navrouterbase.NavFeature
import com.genaku.router.ScreenParams
import com.genaku.router.ScreenResult

interface FeatureB: NavFeature

class FeatureBParams(needReset: Boolean) : ScreenParams

data class FeatureBResult(val name: String) : ScreenResult