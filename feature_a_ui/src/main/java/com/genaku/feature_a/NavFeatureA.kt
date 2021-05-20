package com.genaku.feature_a

import com.genaku.feature_a_api.FeatureA
import com.genaku.feature_a_api.FeatureAParams
import com.genaku.navigator.nav.AbstractNavFeature
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent

class NavFeatureA(override val params: FeatureAParams) :
    AbstractNavFeature(R.id.feature_a_nav_graph, R.id.finishFeatureA), FeatureA
