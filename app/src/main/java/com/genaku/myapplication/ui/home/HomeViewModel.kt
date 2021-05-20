package com.genaku.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.genaku.feature_a.FeatureAResult
import com.genaku.feature_a.createFeatureA
import com.genaku.myapplication.ui.RouterViewModel
import com.genaku.myapplication.ui.dashboard.DashboardScreen
import com.genaku.myapplication.ui.dashboard.DashboardScreenParams
import com.genaku.myapplication.ui.dashboard.DashboardScreenResult
import com.genaku.navigator.FeatureRouter
import org.koin.java.KoinJavaComponent

class HomeViewModel : RouterViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val result: MutableLiveData<String> = MutableLiveData()
    val featureResult = MutableLiveData<String>()

    private val featureRouter: FeatureRouter by KoinJavaComponent.inject(FeatureRouter::class.java)

    fun openDashboard() {
        val screen = DashboardScreen(DashboardScreenParams(name = "First name", id = 20))
        screen.observe<DashboardScreenResult> {
            // передать результат в обработку в usecase или куда-угодно
            // здесь просто выведем на экран
            result.postValue(it.success.toString())
        }
        router.start(screen)
    }

    fun startFeatureA() {
        val feature = createFeatureA(id = 1, films = emptyList())
        feature.observe<FeatureAResult> {
            // передать результат в обработку в usecase или куда-угодно
            // здесь просто выведем на экран
            featureResult.postValue("FeatureA result = $it")
        }
        featureRouter.start(feature)
    }

    fun startFeatureB() {}

    fun startFeatureC() {}
}