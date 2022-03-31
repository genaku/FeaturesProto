package com.genaku.myapplication

import com.genaku.navrouter.PersistentNavRouter
import com.genaku.ui_core.navigationModule
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
            scoped(override = true) { PersistentNavRouter() }
        }
    }
}

object AppScope : KoinScopeComponent {
    override val scope: Scope by newScope()
}