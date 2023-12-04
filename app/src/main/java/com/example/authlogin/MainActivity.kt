package com.example.authlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.authlogin.ui.HomeScreen
import com.example.authlogin.ui.LoginScreen
import com.example.authlogin.ui.theme.AuthLoginTheme

class MainActivity : ComponentActivity() {

    private val isLoggedIn = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthLoginTheme {
                if (isLoggedIn) {
                    HomeScreen()
                } else {
                    LoginScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AuthLoginTheme {
        HomeScreen()
    }
}