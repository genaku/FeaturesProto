package com.genaku.feature_b_ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.genaku.feature_b_ui.databinding.FeatureBStartFragmentBinding
import com.genaku.navigator.getUid
import com.genaku.ui_core.getViewModel
import com.genaku.navigator.nav.FeatureRouter
import com.genaku.navigator.nav.LocalRouter
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

internal class FeatureBStartFragment : Fragment(R.layout.feature_b_start_fragment), KoinComponent {

    private val featureRouter: FeatureRouter by inject()
    private val router: LocalRouter by inject()
    private val viewBinding: FeatureBStartFragmentBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: FeatureBStartViewModel = getViewModel {
            FeatureBStartViewModel(router, featureRouter)
        }

        featureUid = getUid(arguments)!!

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