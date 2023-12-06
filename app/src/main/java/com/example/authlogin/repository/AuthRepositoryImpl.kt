package com.example.authlogin.repository

import android.util.Log
import com.example.authlogin.model.DataProvider.user
import com.example.authlogin.model.FirebaseSignInResponse
import com.example.authlogin.model.Response
import com.example.authlogin.model.SignOutResponse
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
): AuthRepository {
    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser)
//            auth.currentUser?.let {
//                Log.i(TAG, "User: ${it.uid}")
//            }
            Log.i(TAG, "User: ${auth.currentUser?.uid ?: "Not authenticated"}")
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), auth.currentUser)

    override suspend fun signInAnonymously(): FirebaseSignInResponse {
        return try {
            val authResult = auth.signInAnonymously().await()
            authResult?.user?.let { user ->
                Log.i(TAG, "FirebaseAuthSuccess: Anonymous UID: ${user.uid}")
            }
            Response.Success(authResult)
        } catch (error: Exception) {
            Log.e(TAG, "FirebaseAuthError: Failed to Sign in anonymously")
            Response.Failure(error)
        }
    }

    private suspend fun authenticateUser(credential: AuthCredential): FirebaseSignInResponse {
        return try {
            // If we have auth user, link accounts, otherwise sign in.
            val authResult = if (auth.currentUser != null) {
                auth.currentUser!!.linkWithCredential(credential).await()
            } else {
                auth.signInWithCredential(credential).await()
            }

            Log.i(TAG, "User: ${authResult?.user?.uid}")
            Response.Success(authResult)
        }
        catch (error: FirebaseAuthException) {
            Response.Failure(error)
        }
        catch (error: Exception) {
            Response.Failure(error)
        }
    }

    override suspend fun signOut(): SignOutResponse {
        return try {
            // Remove observers
            // TODO: oneTapClient sign out
            auth.signOut()
            Response.Success(true)
        }
        catch (e: java.lang.Exception) {
            Response.Failure(e)
        }
    }

    companion object {
        private const val TAG = "AuthRepository"
    }
}