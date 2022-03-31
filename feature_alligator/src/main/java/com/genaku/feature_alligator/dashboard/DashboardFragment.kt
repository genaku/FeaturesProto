package com.genaku.feature_alligator.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.genaku.alligatorrouter.AlgRouter
import com.genaku.feature_alligator.AlgRouterFragment
import com.genaku.feature_alligator.R
import com.genaku.feature_alligator.databinding.FragmentDashboardBinding
import com.genaku.navrouter.getUid
import com.genaku.ui_core.getViewModel
import com.hoc081098.viewbindingdelegate.viewBinding
import me.aartikov.alligator.annotations.RegisterScreen
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

@RegisterScreen(DashboardScreen::class)
class DashboardFragment : AlgRouterFragment(R.layout.fragment_dashboard) {

    private lateinit var dashboardViewModel: DashboardViewModel

    private val viewBinding by viewBinding(FragmentDashboardBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupButtons()
    }

    override val uuid: UUID
        get() = router.getUUID(this)

    override fun onBack() {
        dashboardViewModel.backWithResult(false)
    }

    private fun setupViewModel() {
        dashboardViewModel = getViewModel { DashboardViewModel(uuid, router) }.apply {
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