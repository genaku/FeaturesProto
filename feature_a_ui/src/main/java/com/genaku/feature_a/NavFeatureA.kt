package com.genaku.feature_a

import com.genaku.feature_a_api.FeatureA
import com.genaku.feature_a_api.FeatureAParams
import com.genaku.navrouterbase.AbstractNavFeature

class NavFeatureA(override val params: FeatureAParams) : AbstractNavFeature(
    destinationResId = R.id.feature_a_nav_graph,
    finishActionResId = R.id.finishFeatureA
), FeatureA
