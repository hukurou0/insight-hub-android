package com.huku.insighthub.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class Screen {
    Splash,
    Auth,
    Home,
}

object ScreenController {
    var currentScreen by mutableStateOf<Screen>(Screen.Splash)
        private set

    fun setScreen(screen: Screen) {
        currentScreen = screen
    }
}
