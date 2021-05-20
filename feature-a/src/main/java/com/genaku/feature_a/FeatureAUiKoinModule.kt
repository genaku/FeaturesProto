package com.genaku.feature_a

import com.genaku.navigator.getNavigationModule
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun getFeatureAKoinModule(): Module {
    loadKoinModules(getNavigationModule())
    return module {
//            viewModel { FeatureAStartViewModel(get()) }
//            viewModel { FeatureAEndViewModel(get()) }

        single { FeatureAStorage() }

        //Регистрируем наш модуль для навигации
        factory<NavFeatureA> { (params: FeatureAParams) -> NavFeatureA(params) }
    }
}