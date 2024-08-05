package com.duongnh.testapp2024.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duongnh.testapp2024.presentation.home.HomeRoute
import com.duongnh.testapp2024.presentation.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TestNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TestNavDestinations.HOME_ROUTE
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier){
        composable(route = TestNavDestinations.HOME_ROUTE) {
            val homeViewModel: HomeViewModel = koinViewModel()
            HomeRoute(homeViewModel = homeViewModel)
        }
    }
}