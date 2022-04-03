package com.genaku.feature_b_ui

import android.os.Bundle
import android.view.View
import com.example.navrouter_api.navrouter.FeatureNavRouter
import com.genaku.feature_b_api.FeatureBResult
import com.genaku.feature_b_ui.databinding.FeatureBEndFragmentBinding
import com.genaku.navrouter.NavRouter
import com.genaku.navrouter.NavRouterFragment
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.core.component.inject

internal class FeatureBEndFragment : NavRouterFragment(R.layout.feature_b_end_fragment, FeatureBScope.scope) {

    private val viewBinding: FeatureBEndFragmentBinding by viewBinding()
    private val featureRouter: FeatureNavRouter by inject()

    override val router: NavRouter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.finishFeatureB.setOnClickListener {
            featureRouter.finishWithResult(featureUid, FeatureBResult("feature b result"))
        }
    }
}