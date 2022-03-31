package com.genaku.feature_b_ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.genaku.feature_b_ui.databinding.FeatureBStartFragmentBinding
import com.genaku.navrouter.NavRouterFragment
import com.genaku.navrouter.FeatureNavRouter
import com.genaku.navrouter.NavRouter
import com.genaku.navrouter.connect
import com.genaku.ui_core.getViewModel
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.core.component.inject

internal class FeatureBStartFragment : NavRouterFragment(R.layout.feature_b_start_fragment, FeatureBScope.scope) {

    private val featureRouter: FeatureNavRouter by inject()
    private val viewBinding: FeatureBStartFragmentBinding by viewBinding()

    override val router: NavRouter by inject()

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
        router.connect(requireActivity(), findNavController())
    }
}