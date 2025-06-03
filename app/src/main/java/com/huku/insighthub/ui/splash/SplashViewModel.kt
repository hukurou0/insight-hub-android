package com.huku.insighthub.ui.splash

import androidx.lifecycle.ViewModel
import com.huku.insighthub.ui.Screen
import com.huku.insighthub.ui.ScreenController
import com.huku.insighthub.usecase.AuthUseCase
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
