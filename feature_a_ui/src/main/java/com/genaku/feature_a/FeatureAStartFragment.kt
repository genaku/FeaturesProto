package com.genaku.feature_a

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.genaku.feature_a.databinding.FeatureAStartFragmentBinding
import com.genaku.ui_core.getUid
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.java.KoinJavaComponent

class FeatureAStartFragment: Fragment(R.layout.feature_a_start_fragment) {

    private val storage: FeatureAStorage by KoinJavaComponent.inject(FeatureAStorage::class.java)

    private val viewBinding by viewBinding(FeatureAStartFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storage.featureUid = getUid(arguments)!!
        viewBinding.goToEndButton.setOnClickListener {
            findNavController().navigate(R.id.action_featureAStartFragment_to_featureAEndFragment)
        }
    }
}