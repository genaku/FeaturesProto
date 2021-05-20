package com.genaku.navigator

import android.os.Bundle
import com.genaku.navigator_core.Router
import kotlinx.coroutines.flow.Flow

interface NavRouter<T: NavScreen>: Router<T> {
    val navCommandFlow: Flow<NavCommand>
    fun getUid(arguments: Bundle?): Long?
}