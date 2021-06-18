package com.genaku.ui_core

import com.genaku.navrouter.*
import com.genaku.router.CommandQueue
import com.genaku.router.IRouterScreens
import org.koin.dsl.bind
import org.koin.dsl.module

fun getNavigationModule() = module {
//    single(override = true) { RouterScreens<NavScreen>() }
    single<IRouterScreens<NavScreen>>(override = true) { RouterScreens() }
    single<CommandQueue<NavCommand>>(override = true) { BaseStorableCommandQueue() } bind BaseStorableCommandQueue::class
    single(override = true) { NavFeatureRouter(get(), get()) }
}