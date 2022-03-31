package com.genaku.feature_alligator.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genaku.alligatorrouter.AlgRouter
import com.genaku.feature_alligator.notifications.NotificationsScreen
import com.genaku.feature_alligator.notifications.NotificationsScreenParams
import com.genaku.feature_alligator.notifications.NotificationsScreenResult
import com.genaku.router.observe
import java.util.*

class DashboardViewModel(private val uuid: UUID, private val router: AlgRouter) : ViewModel() {

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