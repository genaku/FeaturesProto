package com.genaku.ui_core

import android.os.Bundle

internal const val NAV_UID = "navUid"

fun getUid(arguments: Bundle?): Long? =
    arguments?.getLong(NAV_UID)

fun uidToBundle(uid: Long) = Bundle().apply {
    putLong(NAV_UID, uid)
}