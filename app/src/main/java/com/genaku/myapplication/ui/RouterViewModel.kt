package com.genaku.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genaku.navigator.NavRouter
import com.genaku.navigator.NavScreen
import com.genaku.navigator_core.ScreenResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent

open class RouterViewModel : ViewModel(), KoinComponent {

    protected val router: NavRouter<NavScreen> by inject()

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