package com.genaku.feature_b_ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.genaku.feature_b_ui.databinding.FeatureBStartFragmentBinding
import com.genaku.navrouter.NavRouterFragment
import com.genaku.navrouter.PersistentNavFeatureRouter
import com.genaku.navrouter.PersistentNavRouter
import com.genaku.ui_core.getViewModel
import com.hoc081098.viewbindingdelegate.viewBinding
import org.koin.core.component.inject

internal class FeatureBStartFragment : NavRouterFragment(R.layout.feature_b_start_fragment, FeatureBScope.scope) {

    private val featureRouter: PersistentNavFeatureRouter by inject()
    private val viewBinding: FeatureBStartFragmentBinding by viewBinding()

    override val router: PersistentNavRouter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: FeatureBStartViewModel = getViewModel {
            FeatureBStartViewModel(router, featureRouter)
        }

        featureUid = uid

        with(viewBinding) {
            goToFeatureA.setOnClickListener {
                viewModel.navigateToFeatureA()
            }
            goToFeatureBEnd.setOnClickListener {
                viewModel.navigateToFeatureBEnd()
            }
        }
        router.connect(requireActivity(), findNavController())
    }
}

//fun <T : RouterCommand> CommandFlow<T>.connect(
//    LifecycleOwner: LifecycleOwner,
//    navController: NavController
//) = LifecycleOwner.lifecycleScope.launch {
//    commandFlow.flowWithLifecycle(LifecycleOwner.lifecycle, Lifecycle.State.RESUMED)
//        .collect {
//            Log.d("TAF", "collect $it")
//            when (it) {
//                Back -> navController.navigateUp()
//                is Open -> navController.navigate(it.destinationResId, uidToBundle(it.uuid))
//                is BackAction -> navController.navigate(it.actionResId)
//            }
//        }
//}