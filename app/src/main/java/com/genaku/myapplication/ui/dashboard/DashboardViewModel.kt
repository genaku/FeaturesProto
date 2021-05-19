package com.genaku.myapplication.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.genaku.myapplication.ui.RouterViewModel
import com.genaku.myapplication.ui.notifications.NotificationsScreen
import com.genaku.myapplication.ui.notifications.NotificationsScreenArguments

class DashboardViewModel(private val uid: Long) : RouterViewModel() {

    private val args = router.getArguments(uid) as DashboardScreenArguments

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment with args: [$args]"
    }
    val text: LiveData<String> = _text

    fun openNotifications() {
        router.start(NotificationsScreen(NotificationsScreenArguments("My name")))
    }

    fun backWithResult(success: Boolean) {
        router.finishWithResult(uid, DashboardScreenResult(success))
    }
}