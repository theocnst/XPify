package com.xptitans.xpify

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xptitans.xpify.navigation.Screen
import com.xptitans.xpify.screens.FirstPageScreen
import com.xptitans.xpify.screens.LoginScreen
import com.xptitans.xpify.screens.ProfilePageScreen
import com.xptitans.xpify.viewmodels.LoginViewModel

@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = BottomBarScreen.FirstPage.route)
    {
        composable(BottomBarScreen.FirstPage.route)
        {
            FirstPageScreen(navController = navController)
        }
        composable(BottomBarScreen.ProfilePage.route)
        {
            ProfilePageScreen(navController=navController, onLogoutClick = {})
        }

        composable(Screen.LoginScreen.route)
        {
            LoginScreen(navController = navController, loginViewModel = LoginViewModel())
        }
    }
}