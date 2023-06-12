package com.depromeet.whatnow.ui.main

sealed class Destination(
    val route: String
) {
    object Home : Destination(ROUTE_HOME)
    object History : Destination(ROUTE_HISTORY)
    object Alarm : Destination(ROUTE_ALARM)
    object Setting : Destination(ROUTE_SETTING)
    object PromiseAdd : Destination(ROUTE_PROMISE_ADD)


    companion object {
        private const val ROUTE_HOME = "home"
        private const val ROUTE_HISTORY = "history"
        private const val ROUTE_ALARM = "alarm"
        private const val ROUTE_SETTING = "setting"
        private const val ROUTE_PROMISE_ADD= "promise_add"

    }
}
