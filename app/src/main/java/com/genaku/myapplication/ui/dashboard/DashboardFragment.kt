package com.genaku.myapplication.ui.dashboard

import android.os.Bundle
import android.view.View
import com.genaku.myapplication.AppScope
import com.genaku.myapplication.R
import com.genaku.myapplication.databinding.FragmentDashboardBinding
import com.genaku.navrouter.NavRouterFragment
import com.genaku.navrouter.NavRouter
import com.genaku.ui_core.getViewModel
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.android.ext.android.inject

class DashboardFragment : NavRouterFragment(R.layout.fragment_dashboard, AppScope.scope) {

    private lateinit var dashboardViewModel: DashboardViewModel

    private val viewBinding by viewBinding(FragmentDashboardBinding::bind)

    override val router: NavRouter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupButtons()
    }

    override fun onBack() {
        dashboardViewModel.backWithResult(false)
    }

    private fun setupViewModel() {
        dashboardViewModel = getViewModel { DashboardViewModel(uid, router) }.apply {
            text.observe(viewLifecycleOwner) {
                viewBinding.textDashboard.text = it
            }
            result.observe(viewLifecycleOwner) {
                viewBinding.tvAnswer.text = it
            }
        }
    }

    private fun setupButtons() {
        with(viewBinding) {
            button.setOnClickListener {
                dashboardViewModel.openNotifications()
            }
            button2.setOnClickListener {
                dashboardViewModel.backWithResult(true)
            }
        }
    }
}

