package com.genaku.feature_b_ui

import com.genaku.navrouter.NavRouter
import com.genaku.navrouter.navigationModule
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.newScope
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

fun getFeatureBKoinModule(): Module {
    loadKoinModules(navigationModule)
    return module {
        //Регистрируем наш модуль для навигации
        single { Repppo() }
        factory<FeatureB> { (params: FeatureBParams) -> NavFeatureB(params, get()) }

        scope<FeatureBScope> {
            scoped { NavRouter("RouterCommandsB", "RouterScreensB") }
        }
    }
}

object FeatureBScope : KoinScopeComponent {
    override val scope: Scope by newScope()
}