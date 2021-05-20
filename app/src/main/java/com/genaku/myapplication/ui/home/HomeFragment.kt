package com.genaku.myapplication.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.genaku.myapplication.R
import com.genaku.myapplication.databinding.FragmentHomeBinding
import com.genaku.ui_core.getViewModel
import com.genaku.navigator.nav.LocalRouter
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeFragment : Fragment(R.layout.fragment_home), KoinComponent {

    private lateinit var homeViewModel: HomeViewModel

    private val viewBinding by viewBinding(FragmentHomeBinding::bind)

    private val router: LocalRouter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = getViewModel { HomeViewModel(router) }.apply {
            text.observe(viewLifecycleOwner) {
                viewBinding.textHome.text = it
            }
            result.observe(viewLifecycleOwner) {
                viewBinding.tvResult.text = it
            }
            featureResult.observe(viewLifecycleOwner) {
                viewBinding.tvFeatureResult.text = it
            }
        }

        with(viewBinding) {
            button1.setOnClickListener {
                homeViewModel.openDashboard()
            }
            btnFeatureA.setOnClickListener {
                homeViewModel.startFeatureA()
            }
            btnFeatureB.setOnClickListener {
                homeViewModel.startFeatureB()
            }
            btnFeatureC.setOnClickListener {
                homeViewModel.startFeatureC()
            }
        }
    }
}