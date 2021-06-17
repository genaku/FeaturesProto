package com.genaku.router

interface StorableCommandQueue<C : RouterCommand>: CommandQueue<C> {
    fun getCommandsToStore(): List<C>
    fun setCommandsFromStore(storedCommands: List<C>)
}