package com.genaku.myapplication

import com.genaku.navrouter.NavRouter
import com.genaku.navrouter.NavScreen
import com.genaku.router.RouterScreens
import com.genaku.router.StorableRouterScreens
import com.genaku.ui_core.getNavigationModule
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.newScope
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.bind
import org.koin.dsl.module

fun getAppKoinModule(): Module {
    loadKoinModules(
        getNavigationModule()
    )
    return module {
        scope<AppScope> {
            scoped<RouterScreens<NavScreen>>(override = true) { StorableRouterScreens() } bind StorableRouterScreens::class
            scoped(override = true) { NavRouter(get(), get()) }
        }
    }
}

object AppScope : KoinScopeComponent {
    override val scope: Scope by newScope()
}