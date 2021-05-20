package com.genaku.feature_b_ui

import com.genaku.feature_b_api.FeatureB
import com.genaku.feature_b_api.FeatureBParams
import com.genaku.navigator.nav.AbstractNavFeature

class NavFeatureB(override val params: FeatureBParams) :
    AbstractNavFeature(R.id.feature_b_nav_graph, R.id.finishFeatureB), FeatureB

var featureUid: Long = 0