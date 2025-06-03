package com.example.insighthub.ui.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.insighthub.ui.Screen
import com.example.insighthub.ui.ScreenController
import com.example.insighthub.usecase.AuthUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(
    val onDismiss: () -> Unit,
) : ViewModel() {
    var email by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun initialize() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val user = AuthUseCase.getUser()
                if (user?.email != null) {
                    email = user.email.toString()
                }
            } catch (e: Exception) {
                println("Error fetching user: ${e.message}")
            }
        }
    }

    fun logOut() {
        CoroutineScope(Dispatchers.Main).launch {
            isLoading = true
            try {
                AuthUseCase.logOut()
                ScreenController.setScreen(Screen.Auth)
                onDismiss()
            } catch (error: Exception) {
                print(error)
            } finally {
                isLoading = false
            }
        }
    }
}
