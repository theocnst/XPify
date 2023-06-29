package com.xptitans.xpify.feature_xpify.navigation.graphs

import HabitsScreen
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.xptitans.xpify.feature_xpify.navigation.BottomBarScreen
import com.xptitans.xpify.feature_xpify.navigation.HabitPageScreen
import com.xptitans.xpify.feature_xpify.presentation.add_edit_habit.AddEditHabitScreen
import com.xptitans.xpify.feature_xpify.presentation.auth.LoginViewModel
import com.xptitans.xpify.feature_xpify.presentation.auth.RegisterViewModel
import com.xptitans.xpify.feature_xpify.presentation.auth.components.LoginScreen
import com.xptitans.xpify.feature_xpify.presentation.auth.components.RegisterScreen
import com.xptitans.xpify.feature_xpify.presentation.home.components.FirstPageScreen
import com.xptitans.xpify.feature_xpify.presentation.home.components.ProfilePageScreen

@OptIn(ExperimentalAnimationApi::class)
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
            HabitsScreen(navController = navController)
        }
        composable(BottomBarScreen.ProfilePage.route) {
            ProfilePageScreen(navController = navController)
        }
        composable(route = AuthScreen.Login.route) {
            val loginViewModel: LoginViewModel = LoginViewModel()
            LoginScreen(navController = navController, loginViewModel = loginViewModel)
        }
        composable(route = AuthScreen.Register.route) {
            val registerViewModel: RegisterViewModel = RegisterViewModel()
            RegisterScreen(navController = navController, registerViewModel = registerViewModel)
        }

        composable(route = HabitPageScreen.HabitsScreen.route) {
            HabitsScreen(navController = navController)
        }

        composable(
            route = HabitPageScreen.AddEditHabitScreen.route +
                    "?habitId={habitId}&habitColor={habitColor}",
            arguments = listOf(
                navArgument(
                    name = "habitId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "habitColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            val color = it.arguments?.getInt("habitColor") ?: -1
            AddEditHabitScreen(
                navController = navController,
                habitColor = color
            )
        }
    }
}