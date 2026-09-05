package com.finanly.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.finanly.app.presentation.screens.auth.AuthScreen
import com.finanly.app.presentation.screens.dashboard.DashboardScreen
import com.finanly.app.presentation.screens.splash.SplashScreen

@Composable
fun FinanlyNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = FinanlyDestination.Splash.route) {
        composable(FinanlyDestination.Splash.route) {
            SplashScreen(
                onNavigateToAuth = {
                    navController.navigate(FinanlyDestination.Auth.route) {
                        popUpTo(FinanlyDestination.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToDashboard = {
                    navController.navigate(FinanlyDestination.Dashboard.route) {
                        popUpTo(FinanlyDestination.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(FinanlyDestination.Auth.route) { AuthScreen() }
        composable(FinanlyDestination.Dashboard.route) { DashboardScreen() }
    }
}