package com.genaku.feature_b_api

import com.genaku.navigator.base.ScreenResult
import com.genaku.navigator.base.ScreenParams
import com.genaku.navigator.nav.NavFeature

interface FeatureB: NavFeature

class FeatureBParams(needReset: Boolean) : ScreenParams

data class FeatureBResult(val name: String) : ScreenResult