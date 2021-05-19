package com.genaku.navigator_core

interface Router<T: Screen> {
    fun start(screen: T)
    fun finish(uid: Long)
    fun finishWithResult(uid: Long, result: ScreenResult)
    fun getArguments(uid: Long): ScreenArguments?
}