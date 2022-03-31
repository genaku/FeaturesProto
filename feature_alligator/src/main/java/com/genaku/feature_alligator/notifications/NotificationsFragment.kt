package com.genaku.feature_alligator.notifications

import android.os.Bundle
import android.view.View
import com.genaku.feature_alligator.AlgRouterFragment
import com.genaku.feature_alligator.R
import com.genaku.feature_alligator.databinding.FragmentNotificationsBinding
import com.genaku.feature_alligator.notifications.NotificationsViewModel
import com.genaku.ui_core.NavRouterFragment
import com.genaku.ui_core.getViewModel
import com.hoc081098.viewbindingdelegate.viewBinding
import me.aartikov.alligator.annotations.RegisterScreen
import java.util.*

@RegisterScreen(NotificationsScreen::class)
class NotificationsFragment : AlgRouterFragment(R.layout.fragment_notifications) {

    private lateinit var notificationsViewModel: NotificationsViewModel

    private val viewBinding by viewBinding(FragmentNotificationsBinding::bind)

    override val uuid: UUID by lazy { router.getUUID(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationsViewModel = getViewModel { NotificationsViewModel(uuid, router) }
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