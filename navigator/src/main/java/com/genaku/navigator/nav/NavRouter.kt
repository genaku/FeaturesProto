package com.genaku.navigator.nav

import com.genaku.navigator.base.Router
import kotlinx.coroutines.flow.Flow

/**
 * Navigation router based on Navigation Component
 *
 * @author Gena Kuchergin
 */
interface NavRouter<T: NavScreen>: Router<T> {

    /**
     * Navigation commands flow
     */
    val navCommandFlow: Flow<NavCommand>
}

/**
 * Used to define local router
 */
typealias LocalRouter = NavRouter<NavScreen>