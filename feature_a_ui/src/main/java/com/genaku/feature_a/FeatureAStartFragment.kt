package com.genaku.feature_a

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.genaku.feature_a.databinding.FeatureAStartFragmentBinding
import com.genaku.navrouter.getUid
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FeatureAStartFragment: Fragment(R.layout.feature_a_start_fragment), KoinComponent {

    private val storage: FeatureAStorage by inject()

    private val viewBinding by viewBinding(FeatureAStartFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storage.featureUid = getUid(arguments)!!
        viewBinding.goToEndButton.setOnClickListener {
            findNavController().navigate(R.id.action_featureAStartFragment_to_featureAEndFragment)
        }
    }
}