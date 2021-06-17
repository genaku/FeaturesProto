package com.genaku.feature_b_ui

import com.genaku.feature_b_api.FeatureB
import com.genaku.feature_b_api.FeatureBParams
import com.genaku.feature_b_api.Repppo
import com.genaku.navrouter.AbstractNavFeature

class NavFeatureB(override val params: FeatureBParams, override val repo: Repppo) : AbstractNavFeature(
    destinationResId = R.id.feature_b_nav_graph,
    finishActionResId = R.id.finishFeatureB
), FeatureB

var featureUid: Long = 0