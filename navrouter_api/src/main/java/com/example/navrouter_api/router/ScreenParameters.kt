package com.example.navrouter_api.router

import java.util.*

interface ScreenParameters {
    fun getParametersOrNull(uuid: UUID): ScreenParams?
}