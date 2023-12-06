package com.example.authlogin.model

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.StateFlow

//typealias OneTapSignInResponse = Response<BeginSignInResult>
typealias FirebaseSignInResponse = Response<AuthResult>
typealias SignOutResponse = Response<Boolean>
typealias AuthStateResponse = StateFlow<FirebaseUser?>

sealed class Response<out T> {
    object Loading: Response<Nothing>()
    data class Success<out T>(val data: T?): Response<T>()
    data class Failure(val e: Exception): Response<Nothing>()
}