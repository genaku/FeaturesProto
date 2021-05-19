package com.genaku.myapplication.ui.notifications

import android.os.Bundle
import android.view.View
import com.genaku.myapplication.R
import com.genaku.myapplication.databinding.FragmentNotificationsBinding
import com.genaku.myapplication.ui.RouterFragment
import com.genaku.myapplication.ui.getViewModel
import com.hoc081098.viewbindingdelegate.viewBinding

class NotificationsFragment : RouterFragment(R.layout.fragment_notifications) {

    private lateinit var notificationsViewModel: NotificationsViewModel

    private val viewBinding by viewBinding(FragmentNotificationsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationsViewModel = getViewModel { NotificationsViewModel(uid) }
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            viewBinding.textNotifications.text = it
        }
    }
}