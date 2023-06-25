package com.xptitans.xpify.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xptitans.xpify.viewmodels.RegisterViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenUI(
    email: MutableState<String>,
    password: MutableState<String>,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register",
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRegisterClick) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Click here to Login",
            modifier = Modifier.clickable(onClick = onLoginClick)
        )
    }
}

// Logic for the UI
@Composable
fun RegisterScreen(navController: NavController, registerViewModel: RegisterViewModel = viewModel()) {
    val context = LocalContext.current

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    RegisterScreenUI(
        email = email,
        password = password,
        onRegisterClick = {
            if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                registerViewModel.createUserWithEmailAndPassword(
                    context,
                    email.value.trim(),
                    password.value.trim(),
                    onSuccess = { /* Navigate to the login screen or main screen */ },
                    onFailure = { /* Handle the failure */ }
                )
            } else {
                Toast.makeText(context, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        },
        onLoginClick = {
            navController.navigate("login_screen")
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreenUI(
        email = remember { mutableStateOf("") },
        password = remember { mutableStateOf("") },
        onRegisterClick = {},
        onLoginClick = {}
    )
}