package com.genaku.myapplication

import com.genaku.navigator_di.getNavigationModule
import com.genaku.navrouter.NavRouter
import com.genaku.navrouter.StorableNavRouter
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
            scoped { StorableNavRouter(get()) as NavRouter}
        }
    }
}

object AppScope: KoinScopeComponent {
    override val scope: Scope by newScope()
}