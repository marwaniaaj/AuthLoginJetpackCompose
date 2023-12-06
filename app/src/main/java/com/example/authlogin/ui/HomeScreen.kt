@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.authlogin.ui

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.authlogin.AuthViewModel
import com.example.authlogin.R
import com.example.authlogin.model.AuthState
import com.example.authlogin.model.DataProvider
import com.example.authlogin.ui.theme.AuthLoginTheme

@Composable
fun HomeScreen(
    authViewModel: AuthViewModel
) {
    val openLoginDialog = remember { mutableStateOf(false) }
    val authState = DataProvider.authState

    Scaffold(
        topBar = { HomeScreenTopBar() },
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
            Card(
                modifier = Modifier.padding(16.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.tertiary
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    if (authState == AuthState.SignedIn) {
                        Text(
                            DataProvider.user?.displayName ?: "Name Placeholder",
                            fontWeight = FontWeight.Bold
                        )
                        Text(DataProvider.user?.email ?: "Email Placeholder")
                    }
                    else {
                        Text(
                            "Sign-in to view data!"
                        )
                    }
                }
            }

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f),
                painter = painterResource(R.drawable.smartphone),
                contentDescription = "app_logo",
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.tertiary)
            )

            Button(
                onClick = {
                    if (authState != AuthState.SignedIn)
                        openLoginDialog.value = true
                    else
                        authViewModel.signOut()
                },
                modifier = Modifier
                    .size(width = 200.dp, height = 50.dp)
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                )
            ) {
                Text(
                    text = if (authState != AuthState.SignedIn) "Sign-in" else "Sign out",
                    modifier = Modifier.padding(6.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        AnimatedVisibility(visible = openLoginDialog.value) {
            Dialog(
                onDismissRequest = { openLoginDialog.value = false },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false // experimental
                )
            ) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LoginScreen(authViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar() {
    MediumTopAppBar(
        title = {
            Text(
                "Welcome",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.tertiary,
        ),
    )
}


@Preview
@Composable
fun HomeScreenPreview() {
    AuthLoginTheme {
        HomeScreen(hiltViewModel())
    }
}