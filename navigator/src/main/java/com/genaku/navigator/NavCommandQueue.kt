package com.genaku.navigator

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class NavCommandQueue {

    private val channel = Channel<NavCommand>(Channel.UNLIMITED)

    val navCommandFlow = channel.receiveAsFlow()

    fun send(command: NavCommand) {
        channel.trySend(command)
    }
}