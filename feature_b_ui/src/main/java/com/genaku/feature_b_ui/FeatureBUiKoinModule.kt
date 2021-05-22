package com.genaku.feature_b_ui

import com.genaku.feature_b_api.FeatureB
import com.genaku.feature_b_api.FeatureBParams
import com.genaku.navigator_di.getNavigationModule
import com.genaku.navrouterbase.NavRouter
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
        factory<FeatureB> { (params: FeatureBParams) -> NavFeatureB(params) }

        scope<FeatureBScope> {
            scoped { NavRouter(get()) }
        }
    }
}

object FeatureBScope : KoinScopeComponent {
    override val scope: Scope by newScope()
}