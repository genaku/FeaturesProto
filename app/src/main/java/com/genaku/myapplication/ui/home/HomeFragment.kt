package com.genaku.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.genaku.myapplication.AppScope
import com.genaku.myapplication.R
import com.genaku.myapplication.databinding.FragmentHomeBinding
import com.genaku.navrouter.NavRouter
import com.genaku.ui_core.getViewModel
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class HomeFragment : Fragment(R.layout.fragment_home), KoinScopeComponent {

    override val scope: Scope = AppScope.scope

    private lateinit var homeViewModel: HomeViewModel

    private val viewBinding by viewBinding(FragmentHomeBinding::bind)

    private val router: NavRouter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAF", "nav HomeFragment view created")
        setupViewModel()
        setupButtons()
    }

    private fun setupViewModel() {
        homeViewModel = getViewModel { HomeViewModel(router) }.apply {
            text.observe(viewLifecycleOwner) {
                viewBinding.textHome.text = it
            }
            result.observe(viewLifecycleOwner) {
                viewBinding.tvResult.text = "return from Notification with $it"
            }
            featureResult.observe(viewLifecycleOwner) {
                viewBinding.tvFeatureResult.text = it
            }
        }
    }

    private fun setupButtons() {
        with(viewBinding) {
            button1.setOnClickListener {
//                TODO
//                homeViewModel.openDashboard()
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