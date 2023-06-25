package com.xptitans.xpify.screens

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    onLogoutClick: () -> Unit
) {
    ProfilePageScreenUI(
        profilePageViewModel = profilePageViewModel,
        onLogoutClick = {
profilePageViewModel.logout()
            onLogoutClick()
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