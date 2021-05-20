package com.genaku.myapplication

import com.genaku.navigator.NavRouterImpl
import com.genaku.navigator.getNavigationModule
import com.genaku.navigator.nav.NavRouter
import com.genaku.navigator.nav.AbstractNavScreen
import com.genaku.navigator.nav.LocalRouter
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun getAppKoinModule(): Module {
    loadKoinModules(
        getNavigationModule()
    )
    return module {
        single<LocalRouter> { NavRouterImpl(get()) }
    }
}