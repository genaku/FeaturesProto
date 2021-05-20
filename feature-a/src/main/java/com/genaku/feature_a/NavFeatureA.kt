package com.genaku.feature_a

import com.genaku.navigator.NavFeature
import com.genaku.navigator_core.ScreenParams
import com.genaku.navigator_core.ScreenResult
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent

class NavFeatureA(override val params: FeatureAParams) :
    NavFeature(R.id.feature_a_nav_graph, R.id.finishFeatureA), FeatureA

class FeatureAParams(val id: Long, val films: List<String>) : ScreenParams

data class FeatureAResult(val success: Boolean) : ScreenResult

fun createFeatureA(id: Long, films: List<String>): NavFeatureA {
    val params = FeatureAParams(id, films)
    return KoinJavaComponent.get(NavFeatureA::class.java, parameters = { parametersOf(params) })
}