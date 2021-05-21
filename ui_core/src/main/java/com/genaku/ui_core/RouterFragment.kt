package com.genaku.ui_core

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.genaku.navigator.nav.LocalRouter
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinApiExtension
import org.koin.core.scope.KoinScopeComponent
import org.koin.core.scope.Scope
import org.koin.core.scope.newScope

@KoinApiExtension
open class RouterFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId),
    KoinScopeComponent {

    override val scope: Scope by lazy { newScope() }

    protected val router: LocalRouter by inject()
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