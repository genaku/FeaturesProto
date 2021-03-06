package com.genaku.feature_b_ui

import com.genaku.feature_b_api.FeatureB
import com.genaku.feature_b_api.FeatureBParams
import com.genaku.feature_b_api.Repppo
import com.genaku.navrouter.NavRouter
import com.genaku.navrouter.NavScreen
import com.genaku.router.StorableRouterScreens
import com.genaku.ui_core.getNavigationModule
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.newScope
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

fun getFeatureBKoinModule(): Module {
    loadKoinModules(getNavigationModule())
    return module {
        //Регистрируем наш модуль для навигации
        single { Repppo() }
        factory<FeatureB> { (params: FeatureBParams) -> NavFeatureB(params, get()) }

        scope<FeatureBScope> {
            scoped { StorableRouterScreens<NavScreen>() }
            scoped { NavRouter(get(), get()) }
        }
    }
}

object FeatureBScope : KoinScopeComponent {
    override val scope: Scope by newScope()
}