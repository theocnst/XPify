package com.xptitans.xpify.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    fun signInWithEmailAndPassword(
        context: Context,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        // Validate email format
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!email.matches(emailPattern.toRegex())) {
            Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
            return
        }
        // Validate password length
        if (password.length < 6) {
            Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_SHORT)
                .show()
            return
        }

        // Log the email address and password
        Log.d("LoginViewModel", "Email: $email")
        Log.d("LoginViewModel", "Password: $password")

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Authentication successful.", Toast.LENGTH_SHORT).show()
                    onSuccess()
                } else {
                    // Log the error message from Firebase
                    Log.e("LoginViewModel", "Error: ${task.exception?.message}")
                    Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    onFailure()
                }
            }
    }
}
