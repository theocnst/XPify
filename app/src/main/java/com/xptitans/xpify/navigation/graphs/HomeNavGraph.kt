package com.xptitans.xpify.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xptitans.xpify.navigation.BottomBarScreen
import com.xptitans.xpify.screens.FirstPageScreen
import com.xptitans.xpify.screens.ProfilePageScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.FirstPage.route)
    {
        composable(BottomBarScreen.FirstPage.route)
        {
            FirstPageScreen(navController = navController)
        }
        composable(BottomBarScreen.ProfilePage.route)
        {
            ProfilePageScreen(navController=navController)
        }
    }
}