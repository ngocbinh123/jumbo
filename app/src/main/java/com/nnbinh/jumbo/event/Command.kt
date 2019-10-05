package com.nnbinh.jumbo.event

sealed class Command {
    class Snack(
        val message: String? = null, val resId: Int? = null, val isSucceed: Boolean = false,
        val hasSound: Boolean = true
    ) : Command()
    class LogoutSuccess: Command()
}