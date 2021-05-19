package com.genaku.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genaku.navigator.LocalRouter
import com.genaku.navigator.NavRouter
import com.genaku.navigator.NavScreen
import com.genaku.navigator_core.ScreenResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

open class RouterViewModel : ViewModel() {

    protected val router: LocalRouter by KoinJavaComponent.inject(LocalRouter::class.java)

    protected inline fun <reified T : ScreenResult> NavScreen.observe(crossinline block: (T) -> Unit) {
        viewModelScope.launch {
            resultStateFlow.collect {
                if (it is T) {
                    block(it)
                }
            }
        }
    }
}