package com.genaku.myapplication.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.genaku.myapplication.ui.RouterViewModel

class NotificationsViewModel(private val uid: Long) : RouterViewModel() {

    private val args = router.getArguments(uid) as NotificationsScreenArguments

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment with text [${args.message}]"
    }
    val text: LiveData<String> = _text

    fun backWithResult(yes: Boolean) {
        router.finishWithResult(uid, NotificationsScreenResult(if (yes) "positive answer" else "negative answer"))
    }
}