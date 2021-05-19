package com.genaku.myapplication.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.genaku.myapplication.ui.RouterViewModel

class NotificationsViewModel(private val uid: Long) : RouterViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}