package com.finanly.app.presentation.navigation

sealed class FinanlyDestination(val route: String) {
    data object Splash : FinanlyDestination("splash")
    data object Auth : FinanlyDestination("auth")
    data object Dashboard : FinanlyDestination("dashboard")
}