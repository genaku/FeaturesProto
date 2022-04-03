package com.genaku.myapplication.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navrouter_api.router.observe
import com.genaku.myapplication.ui.notifications.NotificationsScreen
import com.genaku.myapplication.ui.notifications.NotificationsScreenParams
import com.genaku.myapplication.ui.notifications.NotificationsScreenResult
import com.genaku.navrouter.NavRouter
import java.util.*

class DashboardViewModel(private val uuid: UUID, private val router: NavRouter) : ViewModel() {

    private val args = router.getParametersOrNull(uuid) as DashboardScreenParams

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment with args: [$args]"
    }
    val text: LiveData<String> = _text

    val result = MutableLiveData<String>()

    fun openNotifications() {
        val screen = NotificationsScreen(NotificationsScreenParams("This is notification message"))
        screen.observe<NotificationsScreenResult>(viewModelScope) {
            result.postValue(it.answer)
        }
        router.start(screen)
    }

    fun backWithResult(success: Boolean) {
        router.finishWithResult(uuid, DashboardScreenResult(success))
    }
}