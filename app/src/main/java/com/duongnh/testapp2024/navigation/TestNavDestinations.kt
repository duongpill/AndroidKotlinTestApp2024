package com.duongnh.testapp2024.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object TestNavDestinations {
    const val HOME_ROUTE = "home"
    const val DETAILS_ROUTE = "details"
}

class TestAppNavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(TestNavDestinations.HOME_ROUTE){
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToDetails: () -> Unit = {
        navController.navigate(TestNavDestinations.DETAILS_ROUTE){
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}