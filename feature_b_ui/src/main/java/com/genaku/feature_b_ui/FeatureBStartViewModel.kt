package com.genaku.feature_b_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genaku.feature_a_api.FeatureA
import com.genaku.feature_a_api.FeatureAParams
import com.genaku.feature_a_api.FeatureAResult
import com.genaku.navigator.nav.observe
import com.genaku.navigator.nav.FeatureRouter
import com.genaku.navigator.nav.LocalRouter
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent

internal class FeatureBStartViewModel(
    private val router: LocalRouter,
    private val featureRouter: FeatureRouter
) : ViewModel() {

    private val timeMutable = MutableLiveData<Long>()
    val time: LiveData<Long> = timeMutable

    val featureResult = MutableLiveData<String>()
    val result = MutableLiveData<String>()

    fun navigateToFeatureA() {
        val feature = createFeatureA(id = 2, films = emptyList())
        feature.observe<FeatureAResult>(viewModelScope) {
            // передать результат в обработку в usecase или куда-угодно
            // здесь просто выведем на экран
            featureResult.postValue("FeatureA result = $it")
        }
        featureRouter.start(feature)
    }

    fun navigateToFeatureBEnd() {
        val screen = FeatureBEndScreen(FeatureBEndScreenParams("This is feature B end message"))
        screen.observe<FeatureBEndScreenResult>(viewModelScope) {
            result.postValue(it.answer)
        }
        router.start(screen)
    }

    private fun createFeatureA(id: Long, films: List<String>): FeatureA {
        val params = FeatureAParams(id, films)
        return KoinJavaComponent.get(FeatureA::class.java, parameters = { parametersOf(params) })
    }
}