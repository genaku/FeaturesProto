package com.genaku.feature_b_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navrouter_api.navrouter.FeatureNavRouter
import com.example.navrouter_api.router.observe
import com.genaku.feature_a_api.FeatureA
import com.genaku.feature_a_api.FeatureAParams
import com.genaku.feature_a_api.FeatureAResult
import com.genaku.navrouter.NavRouter
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent

internal class FeatureBStartViewModel(
    private val router: NavRouter,
    private val featureRouter: FeatureNavRouter
) : ViewModel() {

    private val timeMutable = MutableLiveData<Long>()
    val time: LiveData<Long> = timeMutable

    val featureResult = MutableLiveData<String>()
    val result = MutableLiveData<String>()

    fun navigateToFeatureA() {
        featureRouter.start(
            createFeatureA(id = 2, films = emptyList()).apply {
                observe<FeatureAResult>(viewModelScope) {
                    // передать результат в обработку в usecase или куда-угодно
                    // здесь просто выведем на экран
                    featureResult.postValue("FeatureA result = $it")
                }
            }
        )
    }

    fun navigateToFeatureBEnd() {
        router.start(
            FeatureBEndScreen(FeatureBEndScreenParams("This is feature B end message")).apply {
                observe<FeatureBEndScreenResult>(viewModelScope) {
                    result.postValue(it.answer)
                }
            }
        )
    }

    private fun createFeatureA(id: Long, films: List<String>): FeatureA {
        val params = FeatureAParams(id, films)
        return KoinJavaComponent.get(FeatureA::class.java, parameters = { parametersOf(params) })
    }
}