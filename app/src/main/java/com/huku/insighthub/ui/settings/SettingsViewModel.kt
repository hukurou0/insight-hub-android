package com.huku.insighthub.ui.settings

import android.R.id.message
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

class SettingsViewModel(
    val onDismiss: () -> Unit,
) : ViewModel() {
    private val errorChannel = Channel<String>(Channel.BUFFERED)
    val errorFlow = errorChannel.receiveAsFlow()

    var email by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun initialize() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val user = AuthUseCase.getUser()
                if (user?.email != null) {
                    email = user.email.toString()
                }
            } catch (error: Exception) {
                errorChannel.trySend(error.message.toString())
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
                errorChannel.trySend(error.message.toString())
            } finally {
                isLoading = false
            }
        }
    }
}
