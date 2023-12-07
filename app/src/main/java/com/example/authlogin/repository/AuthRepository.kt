package com.example.authlogin.repository

import com.example.authlogin.model.AuthStateResponse
import com.example.authlogin.model.FirebaseSignInResponse
import com.example.authlogin.model.OneTapSignInResponse
import com.example.authlogin.model.SignOutResponse
import com.google.android.gms.auth.api.identity.SignInCredential
import kotlinx.coroutines.CoroutineScope

interface AuthRepository {

    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse

    suspend fun signInAnonymously(): FirebaseSignInResponse

    suspend fun onTapSignIn(): OneTapSignInResponse

    suspend fun signInWithGoogle(credential: SignInCredential): FirebaseSignInResponse

    suspend fun signOut(): SignOutResponse
}