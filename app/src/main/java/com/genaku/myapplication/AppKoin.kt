package com.genaku.myapplication

import com.genaku.navigator_di.getNavigationModule
import com.genaku.navrouterbase.NavRouter
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.newScope
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

fun getAppKoinModule(): Module {
    loadKoinModules(
        getNavigationModule()
    )
    return module {
        scope<AppScope> {
            scoped { NavRouter(get()) }
        }
    }
}

object AppScope: KoinScopeComponent {
    override val scope: Scope by newScope()
}