package com.xptitans.xpify

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    private lateinit var buttonLogout: Button
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        buttonLogout = findViewById(R.id.btn_logout)
        textView = findViewById(R.id.userDetails)
        user = auth.currentUser!!

        textView.text = user.email

        buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = android.content.Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}