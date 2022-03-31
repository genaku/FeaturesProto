package com.genaku.feature_b_ui

import com.genaku.navrouter.AbstractNavFeature
import java.util.*

class NavFeatureB(override val params: FeatureBParams, override val repo: Repppo) : AbstractNavFeature(
    destinationResId = R.id.feature_b_nav_graph,
    finishActionResId = R.id.finishFeatureB
), FeatureB

var featureUid: UUID = UUID.randomUUID()