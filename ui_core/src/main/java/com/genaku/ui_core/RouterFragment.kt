package com.genaku.ui_core

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.genaku.navrouterbase.NavRouter
import com.genaku.navrouterconnect.getUid
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope
import androidx.activity.addCallback

open class RouterFragment(@LayoutRes contentLayoutId: Int, override val scope: Scope) :
    Fragment(contentLayoutId), KoinScopeComponent {

    protected val router: NavRouter by inject()
    protected val uid by lazy { getUid(arguments)!! } // Если uid == null, значит во фрагмент пришли не через роутер. Может не стоит наследовать его от RouterFragment?

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