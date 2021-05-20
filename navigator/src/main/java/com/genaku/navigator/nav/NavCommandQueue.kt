package com.genaku.navigator.nav

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Navigation commands queue for Navigation Component
 *
 * @author Gena Kuchergin
 */
class NavCommandQueue {

    private val channel = Channel<NavCommand>(Channel.UNLIMITED)

    /**
     * Navigation commands flow used to get commands from queue
     */
    val navCommandFlow = channel.receiveAsFlow()

    /**
     * Send navigation command into queue
     *
     * @param command - navigation command
     */
    fun send(command: NavCommand) {
        channel.trySend(command)
    }
}