package com.genaku.router

import com.example.navrouter_api.router.RouterScreen

interface RouterState<S : RouterScreen, C : RouterCommand>{
    val commandQueue: CommandQueue<C>
    val routerScreens: RouterScreens<S>
}