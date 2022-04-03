package com.genaku.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.navrouter_api.navrouter.FeatureNavRouter
import com.genaku.navrouter.FeatureNavRouterImpl
import com.genaku.navrouter.connect
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity(R.layout.activity_main), KoinScopeComponent {

    override val scope: Scope = AppScope.scope

    private val featureRouter: FeatureNavRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (featureRouter as FeatureNavRouterImpl).connect(this, findNavController(R.id.rootNavHostFragment))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        (featureRouter as FeatureNavRouterImpl).onRestoreInstanceState(savedInstanceState)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        (featureRouter as FeatureNavRouterImpl).onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }
}