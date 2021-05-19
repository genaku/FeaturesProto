package com.genaku.myapplication.ui.dashboard

import android.os.Bundle
import android.view.View
import com.genaku.myapplication.R
import com.genaku.myapplication.databinding.FragmentDashboardBinding
import com.genaku.myapplication.ui.RouterFragment
import com.genaku.myapplication.ui.getViewModel
import com.hoc081098.viewbindingdelegate.viewBinding

class DashboardFragment : RouterFragment(R.layout.fragment_dashboard) {

    private lateinit var dashboardViewModel: DashboardViewModel

    private val viewBinding by viewBinding(FragmentDashboardBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupButtons()
    }

    override fun onBack() {
        dashboardViewModel.backWithResult(false)
    }

    private fun setupViewModel() {
        dashboardViewModel = getViewModel { DashboardViewModel(uid) }.apply {
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

