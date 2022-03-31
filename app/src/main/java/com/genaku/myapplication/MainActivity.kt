package com.genaku.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.genaku.navrouter.PersistentNavFeatureRouter
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity(R.layout.activity_main), KoinScopeComponent {

    override val scope: Scope = AppScope.scope

    private val featureRouter: PersistentNavFeatureRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        featureRouter.connect(this, findNavController(R.id.rootNavHostFragment))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        featureRouter.onRestoreInstanceState(savedInstanceState)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        featureRouter.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }
}