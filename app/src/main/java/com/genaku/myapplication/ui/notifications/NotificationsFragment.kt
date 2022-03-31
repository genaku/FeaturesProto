package com.genaku.myapplication.ui.notifications

import android.os.Bundle
import android.view.View
import com.genaku.myapplication.AppScope
import com.genaku.myapplication.R
import com.genaku.myapplication.databinding.FragmentNotificationsBinding
import com.genaku.navrouter.NavRouterFragment
import com.genaku.navrouter.PersistentNavRouter
import com.genaku.ui_core.getViewModel
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.core.component.inject

class NotificationsFragment : NavRouterFragment(R.layout.fragment_notifications, AppScope.scope) {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override val router: PersistentNavRouter by inject()

    private val viewBinding by viewBinding(FragmentNotificationsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationsViewModel = getViewModel { NotificationsViewModel(uid, router) }
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            viewBinding.textNotifications.text = it
        }
        viewBinding.btnYes.setOnClickListener {
            notificationsViewModel.backWithResult(true)
        }
        viewBinding.btnNo.setOnClickListener {
            notificationsViewModel.backWithResult(false)
        }
    }
}