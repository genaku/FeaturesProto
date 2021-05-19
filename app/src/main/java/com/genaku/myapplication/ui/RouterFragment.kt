package com.genaku.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.genaku.navigator.LocalRouter
import org.koin.android.ext.android.inject

open class RouterFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    protected val router: LocalRouter by inject()
    protected val uid by lazy { router.getUid(arguments)!! } // Если uid == null, значит во фрагмент пришли не через роутер. Может не стоит наследовать его от RouterFragment?

    protected open fun onBack() {
        router.finish(uid)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            onBack()
        }
    }
}