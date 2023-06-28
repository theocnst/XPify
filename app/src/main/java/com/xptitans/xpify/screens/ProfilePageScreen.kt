package com.xptitans.xpify.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xptitans.xpify.navigation.Screen
import com.xptitans.xpify.viewmodels.ProfilePageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePageScreenUI(
    profilePageViewModel: ProfilePageViewModel,
    onLogoutClick: () -> Unit
) {

    val currentUserEmail = profilePageViewModel.getCurrentUser() ?: "Unknown"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to your profile",
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(
            text = "Hello, $currentUserEmail",
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Button(
            onClick = onLogoutClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Logout")
        }
    }


}

//Logic for the UI
@Composable
fun ProfilePageScreen(
    profilePageViewModel: ProfilePageViewModel = viewModel(),
    onLogoutClick: () -> Unit,
    navController: NavController
) {
    ProfilePageScreenUI(
        profilePageViewModel = profilePageViewModel,
        onLogoutClick = {
            profilePageViewModel.logout()
            onLogoutClick()
            navController.navigate("login_screen") { // Navigate to "main_screen" route
                popUpTo("login_screen") { // Clear back stack and create a new instance of the main NavGraph
                    inclusive = true
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewProfilePageScreen() {
    val mockViewModel = object : ProfilePageViewModel() {
        override fun getCurrentUser(): String? {
            // Return a mock user email for the preview
            return "example@example.com"
        }
    }

    ProfilePageScreenUI(
        //profilePageViewModel = ProfilePageViewModel()
        profilePageViewModel = mockViewModel,
        onLogoutClick = {}
    )
}