package com.genaku.navrouter

import org.koin.dsl.module

val navigationModule = module {
    single(override = true) { PersistentNavFeatureRouter("Features") }
}