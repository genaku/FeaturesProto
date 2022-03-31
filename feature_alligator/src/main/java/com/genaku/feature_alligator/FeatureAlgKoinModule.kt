package com.genaku.feature_alligator

import com.genaku.alligatorrouter.AlgRouter
import com.genaku.ui_core.navigationModule
import me.aartikov.alligator.AndroidNavigator
import me.aartikov.alligator.navigationfactories.GeneratedNavigationFactory
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun getAlgFeatureKoinModule(): Module {
    loadKoinModules(navigationModule)
    return module {
        //Регистрируем наш модуль для навигации
        single { AndroidNavigator(GeneratedNavigationFactory()) }
        single(override = true) { AlgRouter(get(), get(), get()) }
    }
}
