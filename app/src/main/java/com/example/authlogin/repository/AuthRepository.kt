package com.example.authlogin.repository

import com.example.authlogin.model.AuthStateResponse
import com.example.authlogin.model.FirebaseSignInResponse
import com.example.authlogin.model.SignOutResponse
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.CoroutineScope

interface AuthRepository {

    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse

    suspend fun signInAnonymously(): FirebaseSignInResponse

    suspend fun signOut(): SignOutResponse
}