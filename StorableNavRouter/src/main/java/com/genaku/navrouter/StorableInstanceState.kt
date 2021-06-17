package com.genaku.navrouter

import android.os.Bundle

interface StorableInstanceState {
    fun onRestoreInstanceState(savedInstanceState: Bundle)
    fun onSaveInstanceState(outState: Bundle)
}