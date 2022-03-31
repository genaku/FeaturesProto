package com.genaku.alligatorrouter

import com.genaku.router.RouterCommand
import java.util.*

sealed class AlgCommand : com.genaku.router.RouterCommand

object Back : AlgCommand()

data class Open(val screen: AlgScreen, val uuid: UUID) : AlgCommand()

data class ResetTo(val screen: AlgScreen, val uuid: UUID) : AlgCommand()

data class Replace(val screen: AlgScreen, val uuid: UUID) : AlgCommand()