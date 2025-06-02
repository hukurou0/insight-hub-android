package com.example.insighthub.ui.splash

import androidx.lifecycle.ViewModel
import com.example.insighthub.ui.Screen
import com.example.insighthub.ui.ScreenController
import com.example.insighthub.usecase.AuthUseCase
import kotlinx.coroutines.delay

class SplashViewModel : ViewModel() {
    suspend fun initialize() {
        delay(1000)
        if (AuthUseCase.isLoggedIn()) {
            ScreenController.setScreen(Screen.Home)
        } else {
            ScreenController.setScreen(Screen.Auth)
        }
    }
}
