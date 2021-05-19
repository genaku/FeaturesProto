package com.genaku.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.genaku.myapplication.databinding.ActivityMainBinding
import com.genaku.navigator.Back
import com.genaku.navigator.LocalRouter
import com.genaku.navigator.Open
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val router: LocalRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeNavigation()
//        val navView: BottomNavigationView = binding.navView

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }

    private fun observeNavigation() {
        val navController = findNavController(R.id.rootNavHostFragment)
        lifecycleScope.launchWhenResumed {
            router.navCommandFlow.collect {
                Log.d("TAG", "nav command $it")
                when (it) {
                    Back -> navController.navigateUp()
                    is Open -> navController.navigate(it.destinationResId, it.args)
                }
            }
        }
    }
}
