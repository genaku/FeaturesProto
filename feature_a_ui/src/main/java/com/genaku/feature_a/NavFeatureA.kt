package com.genaku.feature_a

import android.util.Log
import com.genaku.feature_a_api.FeatureA
import com.genaku.feature_a_api.FeatureAParams
import com.genaku.navrouter.AbstractNavFeature

class NavFeatureA(override val params: FeatureAParams) : AbstractNavFeature(
    destinationResId = R.id.feature_a_nav_graph,
    finishActionResId = R.id.finishFeatureA
), FeatureA {
    override fun beforeStart() {
        Log.d("TAF", "on start feature A")
    }

    override fun onFinish() {
        Log.d("TAF", "on finish feature A")
    }
}
