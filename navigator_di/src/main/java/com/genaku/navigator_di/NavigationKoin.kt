package com.genaku.navigator_di

import com.genaku.navrouterbase.NavCommand
import com.genaku.navrouterbase.NavFeatureRouter
import com.genaku.router.BaseCommandQueue
import com.genaku.router.CommandQueue
import org.koin.dsl.module

fun getNavigationModule() = module {
    single<CommandQueue<NavCommand>>(override = true) { BaseCommandQueue() }
    single(override = true) { NavFeatureRouter(get())  }
}