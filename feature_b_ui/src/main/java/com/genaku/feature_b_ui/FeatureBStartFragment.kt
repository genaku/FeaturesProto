package com.genaku.feature_b_ui

import android.os.Bundle
import android.view.View
import com.genaku.feature_b_ui.databinding.FeatureBStartFragmentBinding
import com.genaku.navigator.nav.FeatureRouter
import com.genaku.ui_core.RouterFragment
import com.genaku.ui_core.getViewModel
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.android.ext.android.inject

internal class FeatureBStartFragment : RouterFragment(R.layout.feature_b_start_fragment) {


    private val featureRouter: FeatureRouter by inject()
    private val viewBinding: FeatureBStartFragmentBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: FeatureBStartViewModel = getViewModel {
            FeatureBStartViewModel(router, featureRouter)
        }

        featureUid = uid

        with(viewBinding) {
            goToFeatureA.setOnClickListener {
                viewModel.navigateToFeatureA()
            }
            goToFeatureBEnd.setOnClickListener {
                viewModel.navigateToFeatureBEnd()
            }
        }
    }
}