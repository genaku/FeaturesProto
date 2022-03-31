package com.genaku.feature_a

import com.genaku.navrouter.AbstractNavFeature

class NavFeatureA(override val params: FeatureAParams) : AbstractNavFeature(
    destinationResId = R.id.feature_a_nav_graph,
    finishActionResId = R.id.finishFeatureA
), FeatureA
