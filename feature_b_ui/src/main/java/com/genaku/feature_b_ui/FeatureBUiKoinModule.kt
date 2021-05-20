package com.genaku.feature_b_ui

import com.genaku.feature_b_api.FeatureB
import com.genaku.feature_b_api.FeatureBParams
import com.genaku.navigator.getNavigationModule
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun getFeatureBKoinModule(): Module {
    loadKoinModules(getNavigationModule())
    return module {
        //Регистрируем наш модуль для навигации
        factory<FeatureB> { (params: FeatureBParams) -> NavFeatureB(params) }
    }
}