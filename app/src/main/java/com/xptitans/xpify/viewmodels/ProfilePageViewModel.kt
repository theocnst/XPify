package com.xptitans.xpify.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

open class ProfilePageViewModel : ViewModel()
{
    //get the current user from firebase
    private lateinit var auth: FirebaseAuth
    open fun getCurrentUser(): String? {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        return user?.email
    }

    fun logout() {
        auth.signOut()
    }
}
