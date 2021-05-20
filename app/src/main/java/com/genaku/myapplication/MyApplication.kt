package com.genaku.myapplication

import android.app.Application
import com.genaku.feature_a.getFeatureAKoinModule
import com.genaku.navigator.*
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDI()
    }

    private fun setupDI() {
        /**
         * Construct your application by injection needed modules.
         */
        startKoin {
            modules(
                listOf(
                    getAppKoinModule(),
                    getFeatureAKoinModule()
                )
            )
        }
    }

}

fun getAppKoinModule(): Module {
    loadKoinModules(
        getNavigationModule()
    )
    return module {
        single<NavRouter<NavScreen>> { NavRouterImpl(get()) }
    }
}