package com.genaku.router

interface IRouterScreens<S : Screen> {
    fun addScreen(screen: S): Long
    fun deleteScreen(uid: Long)
    fun getScreenOrNull(uid: Long): S?
    fun getAllScreens(): MutableMap<Long, S>
    fun setScreens(newScreens: Map<Long, S>)
    fun getParametersOrNull(uid: Long): ScreenParams?
}