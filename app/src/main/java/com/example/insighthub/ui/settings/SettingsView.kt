package com.example.insighthub.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.insighthub.ui.components.OutlinedButton

@Composable
fun SettingsView(onDismiss: () -> Unit) {
    val viewModel = remember { SettingsViewModel(onDismiss) }
    LaunchedEffect(Unit) {
        viewModel.initialize()
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier =
            Modifier
                .height(screenHeight / 2)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "アカウント",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            TextButton(onClick = viewModel.onDismiss) {
                Text(
                    text = "閉じる",
                    color = Color(0xFF1976D2),
                )
            }
        }

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier =
                    Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF222222)),
                contentAlignment = Alignment.Center,
            ) {
                if (viewModel.email.isNotEmpty() && viewModel.email != "null") {
                    Text(
                        text = "${viewModel.email.first().uppercaseChar()}",
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (viewModel.email != "null") viewModel.email else "",
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            OutlinedButton(
                text = "ログアウト",
                onClick = { viewModel.logOut() },
                enabled = viewModel.email != "null",
                isLoading = viewModel.isLoading,
                color = Color.Red,
            )
        }
    }
}
