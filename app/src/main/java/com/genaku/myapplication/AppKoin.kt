package com.genaku.myapplication

import com.genaku.navrouter.NavRouter
import com.genaku.navrouter.navigationModule
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.newScope
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

fun getAppKoinModule(): Module {
    loadKoinModules(
        navigationModule
    )
    return module {
        scope<AppScope> {
            scoped(override = true) { NavRouter("App") }
        }
    }
}

object AppScope : KoinScopeComponent {
    override val scope: Scope by newScope()
}