package com.xptitans.xpify.feature_xpify.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xptitans.xpify.feature_xpify.navigation.BottomBarScreen
import com.xptitans.xpify.feature_xpify.presentation.home.components.FirstPageScreen
import com.xptitans.xpify.feature_xpify.presentation.home.components.ProfilePageScreen
import com.xptitans.xpify.feature_xpify.presentation.habit.components.HabitScreen // import your HabitScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.FirstPage.route
    ) {
        composable(BottomBarScreen.FirstPage.route) {
            FirstPageScreen(navController = navController)
        }
        composable(BottomBarScreen.HabitScreen.route) {
            HabitScreen()
        }
        composable(BottomBarScreen.ProfilePage.route) {
            ProfilePageScreen(navController = navController)
        }
    }
}