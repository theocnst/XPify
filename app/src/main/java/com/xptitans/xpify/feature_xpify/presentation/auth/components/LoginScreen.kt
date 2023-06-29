package com.xptitans.xpify.feature_xpify.presentation.auth.components

import android.graphics.RuntimeShader
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.xptitans.xpify.R
import com.xptitans.xpify.feature_xpify.navigation.graphs.AuthScreen
import com.xptitans.xpify.feature_xpify.navigation.graphs.Graph
import com.xptitans.xpify.feature_xpify.presentation.auth.LoginViewModel
import org.intellij.lang.annotations.Language


@Language("AGSL")
val CUSTOM_SHADER = """
    uniform float2 resolution;
    layout(color) uniform half4 color;
    layout(color) uniform half4 color2;

    half4 main(in float2 fragCoord) {
        float2 uv = fragCoord/resolution.xy;

        float mixValue = distance(uv, vec2(0, 1));
        return mix(color, color2, mixValue);
    }
""".trimIndent()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenUI(
    email: MutableState<String>,
    password: MutableState<String>,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
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
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TicketComposable(modifier = Modifier)
                Spacer(modifier = Modifier.height(16.dp))
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

                Spacer(modifier = Modifier.height(16.dp))
                var interactionSource = remember { MutableInteractionSource() }
                val isPressed by interactionSource.collectIsPressedAsState()
                val unpressedColor = Color(0xFFDB4581)
                val color = if (isPressed) Color.Blue else unpressedColor
                val shape = if (isPressed) MaterialTheme.shapes.extraLarge else MaterialTheme.shapes.medium

                Button(onClick = onLoginClick,
                    interactionSource= interactionSource,
                    colors = ButtonDefaults.buttonColors(color),
                    shape=shape
                ) {
                    Text("Login")
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            )
            {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Don't have an account? ")
                    Text(
                        text = "Register",
                        //get the color from colors.xml file by the name i have given
                        color = colorResource(id = R.color.registerFromLogin),
                        modifier = Modifier.clickable(onClick = onRegisterClick)
                    )
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    )
}

class TicketShape(private val cornerRadius: Float) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            // Draw your custom path here
            path = drawTicketPath(size = size, cornerRadius = cornerRadius)
        )
    }
}

fun drawTicketPath(size: Size, cornerRadius: Float): Path {
    return Path().apply {
        reset()
        // Top left arc
        arcTo(
            rect = Rect(
                left = -cornerRadius,
                top = -cornerRadius,
                right = cornerRadius,
                bottom = cornerRadius
            ),
            startAngleDegrees = 90.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )
        lineTo(x = size.width - cornerRadius, y = 0f)
        // Top right arc
        arcTo(
            rect = Rect(
                left = size.width - cornerRadius,
                top = -cornerRadius,
                right = size.width + cornerRadius,
                bottom = cornerRadius
            ),
            startAngleDegrees = 180.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )
        lineTo(x = size.width, y = size.height - cornerRadius)
        // Bottom right arc
        arcTo(
            rect = Rect(
                left = size.width - cornerRadius,
                top = size.height - cornerRadius,
                right = size.width + cornerRadius,
                bottom = size.height + cornerRadius
            ),
            startAngleDegrees = 270.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )
        lineTo(x = cornerRadius, y = size.height)
        // Bottom left arc
        arcTo(
            rect = Rect(
                left = -cornerRadius,
                top = size.height - cornerRadius ,
                right = cornerRadius,
                bottom = size.height + cornerRadius
            ),
            startAngleDegrees = 0.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )
        lineTo(x = 0f, y = cornerRadius)
        close()
    }
}

@Composable
fun TicketComposable(modifier: Modifier) {
    androidx.compose.material.Text(
        text = "🎉 LOGIN 🎉",
        style = TextStyle(
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Black,
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .wrapContentSize()
            .graphicsLayer {
                shadowElevation = 15.dp.toPx()
                shape = TicketShape(24.dp.toPx())
                clip = true
            }
            .background(color = Color.Black)
            .drawBehind {
                scale(scale = 0.9f) {
                    drawPath(
                        path = drawTicketPath(size = size, cornerRadius = 24.dp.toPx()),
                        color =  Color(0xFFDB4581),
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f))
                        )
                    )
                }
            }
            .padding(start = 40.dp, top = 15.dp, end = 40.dp, bottom = 15.dp)
    )
}

// Logic for the UI
@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel) {

    val context = LocalContext.current

    // Set default values for easier testing
    val email = remember { mutableStateOf("test@test.com") }
    val password = remember { mutableStateOf("testul") }

    LoginScreenUI(

        email = email,
        password = password,
        onLoginClick = {
            if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                loginViewModel.signInWithEmailAndPassword(
                    context,
                    email.value.trim(),
                    password.value.trim(),
                    onSuccess =
                    { /* Navigate to Home Graph */
                        navController.popBackStack()
                        navController.navigate(Graph.HOME)
                    },
                    onFailure = { /* Handle the failure */ }
                )
            } else {
                Toast.makeText(context, "Email and password cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            }
        },
        onRegisterClick = {
            navController.navigate(AuthScreen.Register.route)
        }
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreenUI(
        email = remember { mutableStateOf("") },
        password = remember { mutableStateOf("") },
        onRegisterClick = {},
        onLoginClick = {}
    )
}
