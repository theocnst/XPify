package com.xptitans.xpify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.xptitans.xpify.ui.theme.XPifyTheme

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        setContent {
            XPifyTheme {
                MainContent {
                    FirebaseAuth.getInstance().signOut()
                    val intent = android.content.Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}

@Composable
fun MainContent(onLogoutClick: () -> Unit) {
    val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: "Unknown"

    MainContentDisplay(userEmail = userEmail, onLogoutClick = onLogoutClick)
}

@Composable
fun MainContentDisplay(userEmail: String, onLogoutClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello, $userEmail",
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Button(onClick = onLogoutClick) {
            Text("Logout")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    XPifyTheme {
        MainContentDisplay(userEmail = "mockemail@example.com", onLogoutClick = {})
    }
}
