package com.genaku.feature_alligator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genaku.alligatorrouter.AlgRouter
import com.genaku.feature_alligator.dashboard.DashboardScreen
import com.genaku.feature_alligator.dashboard.DashboardScreenParams
import com.genaku.feature_alligator.dashboard.DashboardScreenResult
import com.genaku.router.observe
import org.koin.core.component.KoinComponent

class HomeViewModel(private val router: AlgRouter) : ViewModel(), KoinComponent {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val result: MutableLiveData<String> = MutableLiveData()
    val featureResult = MutableLiveData<String>()

//    private val featureRouter: NavFeatureRouter by inject()

    fun openDashboard() {
        val screen = DashboardScreen(DashboardScreenParams(name = "First name", id = 20))
        screen.observe<DashboardScreenResult>(viewModelScope) {
            // передать результат в обработку в usecase или куда-угодно
            // здесь просто выведем на экран
            result.postValue(it.success.toString())
        }
        router.start(screen)
    }

/*
    fun startFeatureA() {
        val feature = createFeatureA(id = 1, films = emptyList())
        feature.observe<FeatureAResult>(viewModelScope) {
            // передать результат в обработку в usecase или куда-угодно
            // здесь просто выведем на экран
            featureResult.postValue("FeatureA result = $it")
        }
        featureRouter.start(feature)
    }

    fun startFeatureB() {
        val feature = createFeatureB(true)
        feature.observe<FeatureBResult>(viewModelScope) {
            featureResult.postValue("FeatureB result = $it")
        }
        featureRouter.start(feature)
    }

    fun startFeatureC() {}

    private fun createFeatureA(id: Long, films: List<String>): FeatureA {
        val params = FeatureAParams(id, films)
        return KoinJavaComponent.get(FeatureA::class.java, parameters = { parametersOf(params) })
    }

    private fun createFeatureB(needReset: Boolean): FeatureB {
        val params = FeatureBParams(needReset)
        return KoinJavaComponent.get(FeatureB::class.java, parameters = { parametersOf(params) })
    }
*/
}