package com.xptitans.xpify.feature_xpify.presentation.auth.components

import android.graphics.RuntimeShader
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xptitans.xpify.feature_xpify.navigation.graphs.AuthScreen
import com.xptitans.xpify.feature_xpify.presentation.auth.RegisterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenUI(
    email: MutableState<String>,
    password: MutableState<String>,
    confirmPassword: MutableState<String>,
    onRegisterClick: () -> Unit,
) {

    val Coral = Color(0xFFF3A397)
    val LightYellow = Color(0xFFF8EE94)
    Box(
        modifier = Modifier
            .drawWithCache {
                val shader = RuntimeShader(CUSTOM_SHADER)
                val shaderBrush = ShaderBrush(shader)
                shader.setFloatUniform("resolution", size.width, size.height)
                onDrawBehind {
                    shader.setColorUniform(
                        "color",
                        android.graphics.Color.valueOf(
                            LightYellow.red, LightYellow.green,
                            LightYellow
                                .blue,
                            LightYellow.alpha
                        )
                    )
                    shader.setColorUniform(
                        "color2",
                        android.graphics.Color.valueOf(
                            Coral.red,
                            Coral.green,
                            Coral.blue,
                            Coral.alpha
                        )
                    )
                    drawRect(shaderBrush)
                }
            }
            .fillMaxWidth(),
        content={
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

                val passwordVisible = remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(
                                imageVector = if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = "Toggle password visibility"
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    label = { Text("Confirm Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = onRegisterClick) {
                    Text("Register")
                }
            }
        }
    )
}

// Logic for the UI
@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = viewModel()
) {
    val context = LocalContext.current

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    RegisterScreenUI(
        email = email,
        password = password,
        confirmPassword = confirmPassword,
        onRegisterClick = {
            if (email.value.isNotEmpty() && password.value.isNotEmpty() && confirmPassword.value.isNotEmpty()) {
                if (password.value == confirmPassword.value) {
                    registerViewModel.createUserWithEmailAndPassword(
                        context,
                        email.value.trim(),
                        password.value.trim(),
                        onSuccess =
                        { /* Navigate to the login screen*/
                            navController.navigate(AuthScreen.Login.route)
                        },
                        onFailure =
                        { /* clear the password and email fields */
                            password.value = ""
                            confirmPassword.value = ""
                            email.value = ""
                        }
                    )
                } else {
                    Toast.makeText(context, "Passwords do not match!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(context, "All fields must be completed!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreenUI(
        email = remember { mutableStateOf("") },
        password = remember { mutableStateOf("") },
        confirmPassword = remember { mutableStateOf("") },
        onRegisterClick = {}
    )
}
