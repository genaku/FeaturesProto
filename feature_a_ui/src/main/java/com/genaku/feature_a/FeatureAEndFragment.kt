package com.genaku.feature_a

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.genaku.feature_a.databinding.FeatureAEndFragmentBinding
import com.genaku.feature_a_api.FeatureAResult
import com.genaku.navrouter.NavFeatureRouter
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FeatureAEndFragment: Fragment(R.layout.feature_a_end_fragment), KoinComponent {

    private val featureRouter: NavFeatureRouter by inject()
    private val storage: FeatureAStorage by inject()

    private val viewBinding by viewBinding(FeatureAEndFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.finishFeatureAButton.setOnClickListener {
            featureRouter.finishWithResult(storage.featureUuid, FeatureAResult(true))
        }
    }
}