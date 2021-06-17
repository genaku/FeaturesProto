package com.genaku.myapplication.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.genaku.navrouter.NavRouter

class NotificationsViewModel(private val uid: Long, private val router: NavRouter) : ViewModel() {

    private val args = router.getParametersOrNull(uid) as NotificationsScreenParams

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment with text [${args.message}]"
    }
    val text: LiveData<String> = _text

    fun backWithResult(yes: Boolean) {
        router.finishWithResult(uid, NotificationsScreenResult(if (yes) "positive answer" else "negative answer"))
    }
}