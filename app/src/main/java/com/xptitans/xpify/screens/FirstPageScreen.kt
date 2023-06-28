package com.xptitans.xpify.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xptitans.xpify.viewmodels.FirstPageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstPageScreenUI(
    firstPageViewModel: FirstPageViewModel,
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize())
    {
        Button(
            onClick = { navController.navigate("profile_page") },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp, top = 5.dp)

        ) {
            Icon(
                Icons.Filled.Person,
                contentDescription = "Profile Icon",
                modifier = Modifier.size(20.dp)
            )
        }
    }

    val currentUserEmail = firstPageViewModel.getCurrentUser() ?: "Unknown"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to Xpify",
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(
            text = "Hello, $currentUserEmail",
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}

//Logic for the UI
@Composable
fun FirstPageScreen(
    firstPageViewModel: FirstPageViewModel = viewModel(),
    navController: NavController
) {
    FirstPageScreenUI(
        firstPageViewModel = firstPageViewModel,
        navController = navController
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewFirstPageScreen() {
    val mockViewModel = object : FirstPageViewModel() {
        override fun getCurrentUser(): String? {
            // Return a mock user email for the preview
            return "example@example.com"
        }
    }
    val navControl = rememberNavController()

    FirstPageScreenUI(
        //firstPageViewModel = FirstPageViewModel()
        firstPageViewModel = mockViewModel,
        navController = navControl
    )
}