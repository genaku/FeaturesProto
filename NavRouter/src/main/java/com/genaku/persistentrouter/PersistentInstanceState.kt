package com.genaku.persistentrouter

import android.os.Bundle

interface PersistentInstanceState {
    fun onRestoreInstanceState(savedInstanceState: Bundle)
    fun onSaveInstanceState(outState: Bundle)
}