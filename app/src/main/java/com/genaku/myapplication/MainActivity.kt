package com.genaku.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.genaku.myapplication.databinding.ActivityMainBinding
import com.genaku.navigator.*
import com.hoc081098.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
class MainActivity : AppCompatActivity(R.layout.activity_main), KoinComponent {

    private val router: NavRouter<NavScreen> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeNavigation()
    }

    private fun observeNavigation() {
        val navController = findNavController(R.id.rootNavHostFragment)
        lifecycleScope.launchWhenResumed {
            router.navCommandFlow.collect {
                Log.d("TAF", "nav command $it")
                when (it) {
                    Back -> navController.navigateUp()
                    is Open -> navController.navigate(it.destinationResId, it.args)
                    is Action -> navController.navigate(it.actionResId)
                }
            }
        }
    }
}