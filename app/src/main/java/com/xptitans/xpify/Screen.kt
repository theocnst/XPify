package com.xptitans.xpify

sealed class Screen(val route: String)
{
    object LoginActivity : Screen(route = "login_screen")
    object RegisterActivity : Screen(route = "register_screen")
}
