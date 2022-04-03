package com.genaku.navrouter

import com.example.navrouter_api.navrouter.FeatureNavRouter
import org.koin.dsl.module

val navigationModule = module {
    single(override = true) { FeatureNavRouterImpl("Features") as FeatureNavRouter }
}