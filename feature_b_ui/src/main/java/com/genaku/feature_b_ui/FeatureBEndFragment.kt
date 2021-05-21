package com.genaku.feature_b_ui

import android.os.Bundle
import android.view.View
import com.genaku.feature_b_api.FeatureBResult
import com.genaku.feature_b_ui.databinding.FeatureBEndFragmentBinding
import com.genaku.navigator.nav.FeatureRouter
import com.genaku.ui_core.RouterFragment
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.core.component.inject

internal class FeatureBEndFragment : RouterFragment(R.layout.feature_b_end_fragment) {

    private val viewBinding: FeatureBEndFragmentBinding by viewBinding()
    private val featureRouter: FeatureRouter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.finishFeatureB.setOnClickListener {
            featureRouter.finishWithResult(featureUid, FeatureBResult("feature b result"))
        }
    }
}