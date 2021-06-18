package com.genaku.ui_core

import com.genaku.navrouter.NavCommand
import com.genaku.navrouter.NavFeatureRouter
import com.genaku.navrouter.NavScreen
import com.genaku.router.CommandQueue
import com.genaku.router.RouterScreens
import com.genaku.router.StorableCommandQueue
import com.genaku.router.StorableRouterScreens
import org.koin.dsl.bind
import org.koin.dsl.module

fun getNavigationModule() = module {
//    single(override = true) { RouterScreens<NavScreen>() }
    single<RouterScreens<NavScreen>>(override = true) { StorableRouterScreens() }
    single<CommandQueue<NavCommand>>(override = true) { StorableCommandQueue() } bind StorableCommandQueue::class
    single(override = true) { NavFeatureRouter(get(), get()) }
}