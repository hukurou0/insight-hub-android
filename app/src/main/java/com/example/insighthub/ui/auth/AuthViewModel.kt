import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.insighthub.ui.auth.AuthMode

class AuthViewModel : ViewModel() {
    var mode by mutableStateOf(AuthMode.LogIn)
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var alertMessage by mutableStateOf<String?>(null)

    fun authenticate() {
        isLoading = true
        // Simulate network delay
        // kotlinx.coroutines.GlobalScope.launch {
        //     kotlinx.coroutines.delay(2000)
        //     isLoading = false
        //     alertMessage = if (email.contains("@")) "Success!" else "メールアドレスが正しくありません"
        // }
    }

    fun clearAlert() {
        alertMessage = null
    }
}
