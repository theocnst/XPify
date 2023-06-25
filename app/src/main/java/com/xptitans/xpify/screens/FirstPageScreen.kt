package com.xptitans.xpify.screens

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.xptitans.xpify.viewmodels.FirstPageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstPageScreenUI(
 firstPageViewModel: FirstPageViewModel
    )
{
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
    firstPageViewModel: FirstPageViewModel= viewModel(),
) {
    FirstPageScreenUI(
        firstPageViewModel = firstPageViewModel
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewFirstPageScreen() {
   FirstPageScreenUI(
         firstPageViewModel = FirstPageViewModel()
   )
}