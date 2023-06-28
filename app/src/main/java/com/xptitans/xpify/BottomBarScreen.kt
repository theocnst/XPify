package com.xptitans.xpify

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon: ImageVector
)
{
    object FirstPage: BottomBarScreen("first_page", "First Page", Icons.Default.Home)
    object ProfilePage: BottomBarScreen("profile_page", "Profile Page", Icons.Default.Person)

}
