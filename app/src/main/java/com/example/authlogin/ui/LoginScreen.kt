package com.example.authlogin.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.authlogin.AuthViewModel
import com.example.authlogin.R
import com.example.authlogin.model.AuthState
import com.example.authlogin.model.DataProvider
import com.example.authlogin.ui.theme.AuthLoginTheme

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .wrapContentSize(Alignment.TopCenter),
            Arrangement.spacedBy(8.dp),
            Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f),
                painter = painterResource(R.drawable.loginscreen),
                contentDescription = "app_logo",
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.tertiary)
            )

            Button(
                onClick = {
                     // TODO: Sign in with Google
                },
                modifier = Modifier
                    .size(width = 300.dp, height = 50.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google_logo),
                    contentDescription = ""
                )
                Text(
                    text = "Sign in with Google",
                    modifier = Modifier.padding(6.dp),
                    color = Color.Black.copy(alpha = 0.5f)
                )
            }

            if (DataProvider.authState == AuthState.SignedOut) {
                Button(
                    onClick = {
                        authViewModel.signInAnonymously()
                    },
                    modifier = Modifier
                        .size(width = 200.dp, height = 50.dp)
                        .padding(horizontal = 16.dp),
                ) {
                    Text(
                        text = "Skip",
                        modifier = Modifier.padding(6.dp),
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    AuthLoginTheme {
        //LoginScreen()
    }
}