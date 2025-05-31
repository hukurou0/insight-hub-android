package com.example.insighthub.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.insighthub.usecase.AuthUseCase
import kotlinx.coroutines.delay

@Composable
fun SplashView() {
    LaunchedEffect(Unit) {
        delay(1000)
        if (AuthUseCase.isLoggedIn()) {
            ScreenController.setScreen(Screen.Home)
        } else {
            ScreenController.setScreen(Screen.Auth)
        }
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Insight Hub",
                fontSize = 24.sp,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}
