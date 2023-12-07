package com.example.authlogin.ui.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.authlogin.model.DataProvider
import com.example.authlogin.model.Response

@Composable
fun AnonymousSignIn() {
    when (val anonymousResponse = DataProvider.anonymousSignInResponse) {
        is Response.Loading -> {
            Log.i("Login:AnonymousSignIn", "Loading")
            AuthLoginProgressIndicator()
        }
        is Response.Success -> anonymousResponse.data?.let { authResult ->
            Log.i("Login:AnonymousSignIn", "Success: $authResult")
        }
        is Response.Failure -> LaunchedEffect(Unit) {
            Log.e("Login:AnonymousSignIn", "${anonymousResponse.e}")
        }
    }
}