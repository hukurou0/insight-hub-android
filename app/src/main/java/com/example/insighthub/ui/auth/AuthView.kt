package com.example.insighthub.ui.auth

import AuthViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class AuthMode(
    val label: String,
) {
    LogIn("ログイン"),
    SignUp("新規登録"),
}

@Preview
@Composable
fun AuthView(viewModel: AuthViewModel = AuthViewModel()) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Insight Hub", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "\"読むだけ\"で終わらない\n学びの新体験を",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column {
            TabRow(selectedTabIndex = viewModel.mode.ordinal) {
                AuthMode.entries.forEachIndexed { index, mode ->
                    Tab(
                        text = { Text(mode.label) },
                        selected = viewModel.mode == mode,
                        onClick = { viewModel.mode = mode },
                        enabled = !viewModel.isLoading,
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    label = { Text("メールアドレス") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                )

                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    label = { Text("パスワード") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                )

                Button(
                    onClick = { viewModel.authenticate() },
                    enabled =
                        viewModel.email.isNotBlank() &&
                            viewModel.password.isNotBlank() &&
                            !viewModel.isLoading,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    } else {
                        Text(viewModel.mode.label)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}
