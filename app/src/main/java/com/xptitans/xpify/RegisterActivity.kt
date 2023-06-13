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

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        buttonRegister = findViewById(R.id.btn_register)
        textView = findViewById(R.id.loginNow)

        textView.setOnClickListener {
            val intent = android.content.Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonRegister.setOnClickListener {
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
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext,
                            "Account created.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    } else {
                        Toast.makeText(
                            baseContext,
                            "Authentication creation failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }

}
