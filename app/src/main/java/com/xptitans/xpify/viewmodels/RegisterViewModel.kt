// RegisterViewModel.kt
package com.xptitans.xpify.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    fun createUserWithEmailAndPassword(
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
        Log.d("RegisterViewModel", "Email: $email")
        Log.d("RegisterViewModel", "Password: $password")

//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(context, "Account created.", Toast.LENGTH_SHORT).show()
//                    onSuccess()
//                } else {
//                    // Log the error message from Firebase
//                    Log.e("RegisterViewModel", "Error: ${task.exception?.message}")
//                    Toast.makeText(context, "Account creation failed.", Toast.LENGTH_SHORT).show()
//                    onFailure()
//                }
//            }
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { fetchTask ->
                if (fetchTask.isSuccessful) {
                    val signInMethods = fetchTask.result?.signInMethods
                    if (!signInMethods.isNullOrEmpty()) {
                        // An account with the same email already exists
                        Toast.makeText(context, "An account with the same email already exists", Toast.LENGTH_SHORT).show()
                        onFailure()
                    } else {
                        // Create a new account
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { createTask ->
                                if (createTask.isSuccessful) {
                                    Toast.makeText(context, "Account created.", Toast.LENGTH_SHORT).show()
                                    onSuccess()
                                } else {
                                    // Log the error message from Firebase
                                    Log.e("RegisterViewModel", "Error: ${createTask.exception?.message}")
                                    Toast.makeText(context, "Account creation failed.", Toast.LENGTH_SHORT).show()
                                    onFailure()
                                }
                            }
                    }
                } else {
                    // Log the error message from Firebase
                    Log.e("RegisterViewModel", "Error: ${fetchTask.exception?.message}")
                    Toast.makeText(context, "Failed to fetch account information.", Toast.LENGTH_SHORT).show()
                    onFailure()
                }
            }
    }

}
