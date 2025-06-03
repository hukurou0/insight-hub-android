package com.huku.insighthub.ui.auth
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huku.insighthub.ui.components.OutlinedTextField
import com.huku.insighthub.ui.components.PrimaryButton
import kotlinx.coroutines.flow.collectLatest

enum class AuthMode(
    val label: String,
) {
    LogIn("ログイン"),
    SignUp("新規登録"),
}

@Preview
@Composable
fun AuthView(viewModel: AuthViewModel = remember { AuthViewModel() }) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.errorFlow.collectLatest { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Insight Hub",
                fontSize = 32.sp,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "\"読むだけ\"で終わらない\n学びの新体験を",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.outline,
            )
        }

        Column {
            TabRow(selectedTabIndex = viewModel.mode.ordinal, contentColor = MaterialTheme.colorScheme.primary) {
                AuthMode.entries.forEachIndexed { index, mode ->
                    Tab(
                        text = { Text(mode.label) },
                        selected = viewModel.mode.ordinal == index,
                        onClick = { viewModel.onSelectAuthMode(mode) },
                        enabled = !viewModel.isLoading,
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(
                    label = "メールアドレス",
                    text = viewModel.email,
                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next,
                        ),
                ) {
                    viewModel.email = it
                }

                OutlinedTextField(
                    label = "パスワード",
                    text = viewModel.password,
                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done,
                        ),
                    visualTransformation = if (viewModel.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image =
                            if (viewModel.passwordVisible) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            }

                        IconButton(onClick = { viewModel.togglePasswordVisibility() }) {
                            Icon(
                                imageVector = image,
                                contentDescription = if (viewModel.passwordVisible) "Hide password" else "Show password",
                            )
                        }
                    },
                ) {
                    viewModel.password = it
                }

                PrimaryButton(
                    text = viewModel.mode.label,
                    onClick = { viewModel.authenticate() },
                    enabled =
                        viewModel.email.isNotEmpty() && viewModel.password.isNotEmpty(),
                )
            }
        }
    }
}
