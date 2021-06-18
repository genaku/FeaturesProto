package com.genaku.myapplication

import com.genaku.navrouter.NavRouter
import com.genaku.navrouter.NavScreen
import com.genaku.navrouter.RouterScreens
import com.genaku.router.IRouterScreens
import com.genaku.ui_core.getNavigationModule
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
            scoped<IRouterScreens<NavScreen>>(override = true) { RouterScreens() }
            scoped(override = true) { NavRouter(get(), get()) }
        }
    }
}

object AppScope : KoinScopeComponent {
    override val scope: Scope by newScope()
}