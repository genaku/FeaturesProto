package com.genaku.myapplication

import android.app.Application
import com.genaku.feature_a.getFeatureAKoinModule
import com.genaku.feature_b_ui.getFeatureBKoinModule
import org.koin.core.context.startKoin

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
                    getFeatureBKoinModule(),
                )
            )
        }
    }
}