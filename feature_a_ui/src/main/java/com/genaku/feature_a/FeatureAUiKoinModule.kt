package com.genaku.feature_a

import com.genaku.feature_a_api.FeatureA
import com.genaku.feature_a_api.FeatureAParams
import com.genaku.ui_core.getNavigationModule
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun getFeatureAKoinModule(): Module {
    loadKoinModules(getNavigationModule())
    return module {
        single { FeatureAStorage() }

        //Регистрируем наш модуль для навигации
        factory<FeatureA> { (params: FeatureAParams) -> NavFeatureA(params) }
    }
}