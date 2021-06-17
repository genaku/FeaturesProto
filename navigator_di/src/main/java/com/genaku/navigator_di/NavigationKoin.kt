package com.genaku.navigator_di

import com.genaku.navrouter.NavCommand
import com.genaku.navrouter.NavFeatureRouter
import com.genaku.router.BaseStorableCommandQueue
import com.genaku.router.StorableCommandQueue
import org.koin.dsl.module

fun getNavigationModule() = module {
    single<StorableCommandQueue<NavCommand>>(override = true) { BaseStorableCommandQueue() }
    single(override = true) { NavFeatureRouter(get())  }
}