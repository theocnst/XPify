package com.xptitans.xpify

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController
)  {
    NavHost(navController = navController, startDestination = Screen.LoginActivity.route)
    {
        composable(route = Screen.LoginActivity.route)
        {
            LoginActivity()
        }
        composable(route = Screen.RegisterActivity.route)
        {
            RegisterActivity()
        }

    }
}