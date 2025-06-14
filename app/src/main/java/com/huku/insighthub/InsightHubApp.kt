package com.huku.insighthub

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.huku.insighthub.ui.Screen
import com.huku.insighthub.ui.ScreenController
import com.huku.insighthub.ui.SplashView
import com.huku.insighthub.ui.auth.AuthView
import com.huku.insighthub.ui.home.HomeView
import com.huku.insighthub.ui.theme.InsightHubTheme

@Preview
@Composable
fun InsightHubApp() {
    InsightHubTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when (ScreenController.currentScreen) {
                    Screen.Splash -> SplashView()
                    Screen.Auth -> AuthView()
                    Screen.Home -> HomeView()
                }
            }
        }
    }
}
