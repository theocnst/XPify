package com.xptitans.xpify.feature_xpify.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.List // you can use another icon
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object FirstPage : BottomBarScreen("first_page", "First Page", Icons.Default.Home)
    object HabitScreen : BottomBarScreen("habit_screen", "Habit Screen", Icons.Default.List)
    object ProfilePage : BottomBarScreen("profile_page", "Profile Page", Icons.Default.Person)
}