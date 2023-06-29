package com.xptitans.xpify.feature_xpify.navigation.graphs

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.xptitans.xpify.feature_xpify.presentation.auth.components.LoginScreen
import com.xptitans.xpify.feature_xpify.presentation.auth.components.RegisterScreen
import com.xptitans.xpify.feature_xpify.presentation.auth.LoginViewModel
import com.xptitans.xpify.feature_xpify.presentation.auth.RegisterViewModel

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route) {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(navController = navController, loginViewModel = loginViewModel)
        }
        composable(route = AuthScreen.Register.route) {
            val registerViewModel: RegisterViewModel = viewModel()
            RegisterScreen(navController = navController, registerViewModel = registerViewModel)
        }
    }
}
sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object Register : AuthScreen(route = "REGISTER")
}