package com.genaku.navrouter

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope
import java.util.*

abstract class NavRouterFragment(@LayoutRes contentLayoutId: Int, override val scope: Scope) :
    Fragment(contentLayoutId), KoinScopeComponent {

    protected abstract val router: PersistentNavRouter

    protected val uid: UUID by lazy { getUid(arguments)!! } // Если uid == null, значит во фрагмент пришли не через роутер. Может не стоит наследовать его от RouterFragment?

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