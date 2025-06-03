package com.example.insighthub.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.insighthub.ui.components.PrimaryButton
import com.example.insighthub.ui.creation.CreationView
import com.example.insighthub.ui.settings.SettingsView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: HomeViewModel = remember { HomeViewModel() }) {
    val coroutineScope = rememberCoroutineScope()
    val addingSheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        )
    val settingsSheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        )
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Insight Hub",
                fontSize = 32.sp,
                style = MaterialTheme.typography.titleLarge,
            )

            IconButton(
                onClick = { viewModel.onTapSettingsButton() },
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier.size(24.dp),
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = { viewModel.onTapAddBookButton() },
                shape = CircleShape,
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF222222),
                        contentColor = Color.White,
                    ),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(24.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "本を追加",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PrimaryButton(text = "Web 版を開く", onClick = { viewModel.onTapOpenInWebButton(context) })
        }
    }

    if (viewModel.addedBook != null) {
        CompletionOverlay(
            viewModel.addedBook!!,
            onOpenBookInBrowser = { viewModel.onTapOpenInWebButton(context, it) },
            onClose = { viewModel.addedBook = null },
        )
    }

    if (viewModel.isSettingsSheetPresented) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.isSettingsSheetPresented = false },
            sheetState = settingsSheetState,
        ) {
            SettingsView {
                coroutineScope.launch {
                    settingsSheetState.hide()
                    viewModel.isSettingsSheetPresented = false
                }
            }
        }
    }

    if (viewModel.isAddingSheetPresented) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.isAddingSheetPresented = false },
            sheetState = addingSheetState,
            modifier = Modifier.padding(top = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding()),
        ) {
            CreationView { book ->
                coroutineScope.launch {
                    addingSheetState.hide()
                    viewModel.isAddingSheetPresented = false
                    viewModel.addedBook = book
                }
            }
        }
    }
}
