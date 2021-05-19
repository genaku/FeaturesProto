package com.genaku.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.genaku.myapplication.ui.RouterViewModel
import com.genaku.myapplication.ui.dashboard.DashboardScreen
import com.genaku.myapplication.ui.dashboard.DashboardScreenArguments
import com.genaku.myapplication.ui.dashboard.DashboardScreenResult

class HomeViewModel : RouterViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val result: MutableLiveData<String> = MutableLiveData()

    fun openDashboard() {
        val screen = DashboardScreen(DashboardScreenArguments("First name", 20))
        screen.observe<DashboardScreenResult> {
            result.postValue(it.success.toString())
        }
        router.start(screen)
    }
}