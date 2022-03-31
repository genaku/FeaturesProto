package com.genaku.feature_alligator

import android.os.Bundle
import android.util.Log
import android.view.View
import com.genaku.feature_alligator.databinding.FragmentHomeBinding
import com.genaku.ui_core.getViewModel
import com.hoc081098.viewbindingdelegate.viewBinding
import me.aartikov.alligator.annotations.RegisterScreen
import org.koin.core.component.KoinComponent
import java.util.*

@RegisterScreen(HomeScreen::class)
class HomeFragment : AlgRouterFragment(R.layout.fragment_home), KoinComponent {

    private lateinit var homeViewModel: HomeViewModel

    private val viewBinding by viewBinding(FragmentHomeBinding::bind)

    override val uuid: UUID by lazy { router.getUUID(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAF", "alg HomeFragment view created")
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
                homeViewModel.openDashboard()
            }
            btnFeatureA.setOnClickListener {
//                homeViewModel.startFeatureA()
            }
            btnFeatureB.setOnClickListener {
//                homeViewModel.startFeatureB()
            }
            btnFeatureC.setOnClickListener {
//                homeViewModel.startFeatureC()
            }
        }
    }
}