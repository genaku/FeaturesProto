package com.genaku.myapplication.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.genaku.myapplication.ui.RouterViewModel
import com.genaku.myapplication.ui.notifications.NotificationsScreen
import com.genaku.myapplication.ui.notifications.NotificationsScreenParams
import com.genaku.myapplication.ui.notifications.NotificationsScreenResult

class DashboardViewModel(private val uid: Long) : RouterViewModel() {

    private val args = router.getArguments(uid) as DashboardScreenParams

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment with args: [$args]"
    }
    val text: LiveData<String> = _text

    val result = MutableLiveData<String>()

    fun openNotifications() {
        val screen = NotificationsScreen(NotificationsScreenParams("This is notification message"))
        screen.observe<NotificationsScreenResult> {
            result.postValue(it.answer)
        }
        router.start(screen)
    }

    fun backWithResult(success: Boolean) {
        router.finishWithResult(uid, DashboardScreenResult(success))
    }
}