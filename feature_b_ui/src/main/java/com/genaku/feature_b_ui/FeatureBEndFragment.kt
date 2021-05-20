package com.genaku.feature_b_ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.genaku.feature_b_api.FeatureBResult
import com.genaku.feature_b_ui.databinding.FeatureBEndFragmentBinding
import com.genaku.navigator.nav.FeatureRouter
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class FeatureBEndFragment : Fragment(R.layout.feature_b_end_fragment), KoinComponent {

    private val viewBinding: FeatureBEndFragmentBinding by viewBinding()
    private val featureRouter: FeatureRouter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.finishFeatureB.setOnClickListener {
            featureRouter.finishWithResult(featureUid, FeatureBResult("feature b result"))
        }
    }
}