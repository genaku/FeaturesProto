package com.genaku.feature_alligator

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.genaku.alligatorrouter.AlgRouter
import com.genaku.navrouter.getUid
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

abstract class AlgRouterFragment(@LayoutRes contentLayoutId: Int): Fragment(contentLayoutId),
    KoinComponent {

    protected abstract val uuid: UUID

    protected val router: AlgRouter by inject()

    protected open fun onBack() {
        router.finish(uuid)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            onBack()
        }
    }
}