package com.xptitans.xpify.navigation

sealed class Screen(val route: String)
{
    object LoginScreen : Screen(route = "login_screen")
    object RegisterScreen : Screen(route = "register_screen")
    object FirstPage : Screen(route = "first_page")
}
