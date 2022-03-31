package com.genaku.storablerouter

import android.os.Bundle

interface PersistentInstanceState {
    fun onRestoreInstanceState(savedInstanceState: Bundle)
    fun onSaveInstanceState(outState: Bundle)
}