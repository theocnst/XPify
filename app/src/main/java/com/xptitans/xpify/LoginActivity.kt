package com.xptitans.xpify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var textView: TextView

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {
        val intent = android.content.Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        buttonLogin = findViewById(R.id.btn_login)
        textView = findViewById(R.id.registerNow)

        textView.setOnClickListener {
            val intent = android.content.Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty()) {
                editTextEmail.error = "Please enter email"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                editTextPassword.error = "Please enter password"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext,
                            "Authentication successful.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        val user = auth.currentUser
                        val intent = android.content.Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }
}