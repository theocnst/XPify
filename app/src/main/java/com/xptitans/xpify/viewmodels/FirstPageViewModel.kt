package com.xptitans.xpify.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class FirstPageViewModel : ViewModel()
{
//get the current user from firebase
    private lateinit var auth: FirebaseAuth
    fun getCurrentUser(): String? {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        return user?.email
    }


}