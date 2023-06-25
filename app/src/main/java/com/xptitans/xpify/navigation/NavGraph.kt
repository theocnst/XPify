package com.xptitans.xpify.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xptitans.xpify.screens.FirstPageScreen
import com.xptitans.xpify.screens.LoginScreen
import com.xptitans.xpify.screens.RegisterScreen
import com.xptitans.xpify.viewmodels.FirstPageViewModel
import com.xptitans.xpify.viewmodels.LoginViewModel
import com.xptitans.xpify.viewmodels.RegisterViewModel

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login_screen") {
        composable(route = "login_screen") {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(navController = navController, loginViewModel = loginViewModel)
        }
        composable(route = "register_screen") {
            val registerViewModel: RegisterViewModel = viewModel()
            RegisterScreen(navController = navController, registerViewModel = registerViewModel)
        }

        composable(route = "first_page") {
            val firstPageViewModel: FirstPageViewModel = viewModel()
            FirstPageScreen(firstPageViewModel = firstPageViewModel)
        }


    }
}

