package com.genaku.navigator

import com.genaku.navigator.nav.FeatureRouter
import com.genaku.navigator.nav.NavCommandQueue
import org.koin.dsl.module

fun getNavigationModule() = module {
    single(override = true) { NavCommandQueue() }
    single<FeatureRouter>(override = true) { FeatureRouterImpl(get())  }
}