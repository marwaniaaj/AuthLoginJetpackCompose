@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.authlogin.R
import com.example.authlogin.ui.theme.AuthLoginTheme

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { HomeScreenTopBar() },
        containerColor = MaterialTheme.colorScheme.primary
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues).padding(16.dp)
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
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text("Name Placeholder", fontWeight = FontWeight.Bold,)
                    Text("Email Placeholder")
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
                    // TODO: Sign out
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
                    text = "Sign out",
                    modifier = Modifier.padding(6.dp),
                    color = MaterialTheme.colorScheme.primary
                )
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
        HomeScreen()
    }
}