package com.genaku.navigator

import android.os.Bundle

sealed class NavCommand

object Back : NavCommand()

data class Open(val destinationResId: Int, val args: Bundle) : NavCommand()