package com.genaku.myapplication

import android.app.Application
import com.genaku.navigator.LocalRouter
import com.genaku.navigator.NavRouter
import com.genaku.navigator.NavRouterImpl
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
                    AppKoinModule.create(),
                )
            )
        }
    }

}

object AppKoinModule {
    fun create(): Module {
        return module {
            single<LocalRouter> { NavRouterImpl() }
        }
    }
}