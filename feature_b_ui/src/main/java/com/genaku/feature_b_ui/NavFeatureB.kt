package com.genaku.feature_b_ui

import com.genaku.feature_b_api.FeatureB
import com.genaku.feature_b_api.FeatureBParams
import com.genaku.navrouterbase.AbstractNavFeature

class NavFeatureB(override val params: FeatureBParams) : AbstractNavFeature(
    destinationResId = R.id.feature_b_nav_graph,
    finishActionResId = R.id.finishFeatureB
), FeatureB

var featureUid: Long = 0