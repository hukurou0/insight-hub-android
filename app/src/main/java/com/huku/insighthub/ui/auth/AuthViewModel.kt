package com.huku.insighthub.ui.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.huku.insighthub.ui.Screen
import com.huku.insighthub.ui.ScreenController
import com.huku.insighthub.usecase.AuthUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val errorChannel = Channel<String>(Channel.BUFFERED)
    val errorFlow = errorChannel.receiveAsFlow()

    var mode by mutableStateOf(AuthMode.LogIn)
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordVisible by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    fun authenticate() {
        isLoading = true
        CoroutineScope(Dispatchers.Main).launch {
            try {
                when (mode) {
                    AuthMode.LogIn -> logIn()
                    AuthMode.SignUp -> signUp()
                }
                ScreenController.setScreen(Screen.Home)
            } catch (error: Exception) {
                errorChannel.trySend(error.message.toString())
            } finally {
                isLoading = false
            }
        }
    }

    fun onSelectAuthMode(mode: AuthMode) {
        val newMode = if (mode == AuthMode.LogIn) AuthMode.LogIn else AuthMode.SignUp
        this.mode = newMode
    }

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    private suspend fun logIn() {
        AuthUseCase.logIn(email, password)
    }

    private suspend fun signUp() {
        AuthUseCase.signUp(email, password)
    }
}
