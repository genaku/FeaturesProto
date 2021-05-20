package com.genaku.feature_a

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hoc081098.viewbindingdelegate.viewBinding
import com.genaku.feature_a.databinding.FeatureAEndFragmentBinding
import com.genaku.navigator.FeatureRouter
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class FeatureAEndFragment: Fragment(R.layout.feature_a_end_fragment), KoinComponent {

    private val featureRouter: FeatureRouter by inject()
    private val storage: FeatureAStorage by inject()

    private val viewBinding by viewBinding(FeatureAEndFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.finishFeatureAButton.setOnClickListener {
            featureRouter.finishWithResult(storage.featureUid, FeatureAResult(true))
        }
    }
}