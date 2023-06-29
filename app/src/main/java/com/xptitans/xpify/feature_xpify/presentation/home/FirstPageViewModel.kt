package com.xptitans.xpify.feature_xpify.presentation.home

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

open class FirstPageViewModel : ViewModel()
{
//get the current user from firebase
    private lateinit var auth: FirebaseAuth
    open fun getCurrentUser(): String? {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        return user?.email
    }
}