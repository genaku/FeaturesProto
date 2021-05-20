package com.genaku.navigator

import org.koin.dsl.module

fun getNavigationModule() = module {
    single(override = true) { NavCommandQueue() }
    single(override = true) { FeatureRouter(get())  }
}