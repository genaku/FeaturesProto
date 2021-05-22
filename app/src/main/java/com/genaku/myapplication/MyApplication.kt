package com.genaku.myapplication

import android.app.Application
import com.genaku.feature_a.getFeatureAKoinModule
import com.genaku.feature_b_ui.getFeatureBKoinModule
import org.koin.android.scope.createScope
import org.koin.core.context.startKoin
import org.koin.core.scope.Scope

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDI()
    }

    private fun setupDI() {
        startKoin {
            modules(
                listOf(
                    getAppKoinModule(),
                    getFeatureAKoinModule(),
                    getFeatureBKoinModule()
                )
            )
        }
    }
}