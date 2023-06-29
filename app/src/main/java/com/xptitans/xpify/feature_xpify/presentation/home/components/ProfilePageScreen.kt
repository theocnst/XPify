package com.xptitans.xpify.feature_xpify.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xptitans.xpify.feature_xpify.navigation.graphs.AuthScreen
import com.xptitans.xpify.feature_xpify.navigation.graphs.Graph
import com.xptitans.xpify.feature_xpify.presentation.home.ProfilePageViewModel

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfilePageScreenUI(
    profilePageViewModel: ProfilePageViewModel,
    onLogoutClick: () -> Unit
) {

    val currentUserEmail = profilePageViewModel.getCurrentUser() ?: "Unknown"
    val updatedNickname = remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            Brush.linearGradient
                (
                colors = listOf(
                    Color(0xFFF3A397),
                    Color(0xFFF8EE94)
                )
            )
        ),
        content = {

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

                val showDataStorageExample = remember { mutableStateOf(false) }

                Button(onClick = { showDataStorageExample.value = true  }) {
                    Text("CreateNickname")
                }

                if(showDataStorageExample.value) {
                    val context = LocalContext.current
                    val keyboardController = LocalSoftwareKeyboardController.current
                    val tokenValue = remember {
                        mutableStateOf(TextFieldValue())
                    }

                    val store = UserStore(context)
                    val tokenText = store.getAccessToken.collectAsState(initial = "")

                    Column(
                        modifier = Modifier.clickable { keyboardController?.hide() },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))

                        Text(text = "Insert your nickname!", fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.height(15.dp))

                        Text(text = "Here's your nickname: ${tokenText.value}")

                        Spacer(modifier = Modifier.height(15.dp))

                        TextField(
                            value = tokenValue.value,
                            onValueChange = { tokenValue.value = it },
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                store.saveToken(tokenValue.value.text)
                                updatedNickname.value = tokenValue.value.text
                            }
                            //showDataStorageExample.value = false
                        }
                    ) {
                        Text(text = "Update Nickname!")
                    }

                }
                Button(
                    onClick = onLogoutClick,
                    modifier = Modifier.padding(top = 16.dp),
                ) {
                    Text("Logout")
                }
            }

        }
    )

}

//Logic for the UI
@Composable
fun ProfilePageScreen(
    profilePageViewModel: ProfilePageViewModel = viewModel(),
    navController: NavController,
) {
    ProfilePageScreenUI(
        profilePageViewModel = profilePageViewModel,
        onLogoutClick = {
            profilePageViewModel.logout()
            navController.popBackStack(Graph.HOME, inclusive = true)
            navController.navigate(AuthScreen.Login.route)
        }
    )
}

//Preview the UI
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


