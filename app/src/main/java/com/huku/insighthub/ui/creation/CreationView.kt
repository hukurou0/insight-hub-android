package com.huku.insighthub.ui.creation

import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.huku.insighthub.enum.BookCategory
import com.huku.insighthub.ui.components.OutlinedTextField
import com.huku.insighthub.ui.components.PickerMenu
import com.huku.insighthub.ui.components.PrimaryButton
import kotlinx.coroutines.flow.collectLatest
import org.openapitools.client.models.BookModel
import java.io.File

@Composable
fun CreationView(onDismiss: (BookModel?) -> Unit) {
    val context = LocalContext.current
    val viewModel = remember { CreationViewModel(onDismiss) }
    val imagePicker =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
        ) { uri: Uri? ->
            uri?.let {
                viewModel.selectImage(context, uri)
            }
        }
    val cameraPicker =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
        ) { success ->
            if (success && viewModel.takenPhotoUri != null) {
                viewModel.selectImage(context, viewModel.takenPhotoUri!!)
            } else {
                viewModel.selectedImage = null
            }
            viewModel.takenPhotoUri = null
        }

    fun openCamera() {
        val file = File.createTempFile("photo_", ".jpg", context.cacheDir)
        val uri =
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file,
            )
        viewModel.selectedImage = file
        viewModel.takenPhotoUri = uri
        cameraPicker.launch(uri)
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
        ) { isGranted: Boolean ->
            if (isGranted) {
                openCamera()
            } else {
                Toast.makeText(context, "カメラのアクセス許可が必要です", Toast.LENGTH_SHORT).show()
            }
        }

    fun grantCameraPermission() {
        permissionLauncher.launch(Manifest.permission.CAMERA)
    }

    LaunchedEffect(Unit) {
        viewModel.errorFlow.collectLatest { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    TextButton(
                        onClick = { onDismiss(null) },
                        enabled = !viewModel.isCreating,
                    ) {
                        Text(text = "キャンセル")
                    }

                    if (viewModel.isCreating) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    } else {
                        TextButton(
                            onClick = { viewModel.createBook() },
                            enabled = viewModel.bookAnalysisResult != null && !viewModel.isCreating,
                        ) {
                            Text(text = "登録")
                        }
                    }
                }

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "本の登録",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                }
            }

            if (viewModel.selectedImage == null) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .background(Color(0xFF222222), RoundedCornerShape(16.dp))
                            .padding(top = 16.dp),
                    contentAlignment = Alignment.Center,
                ) {}
            } else {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    AsyncImage(
                        model = viewModel.selectedImage,
                        contentDescription = "Book Cover",
                        modifier =
                            Modifier
                                .heightIn(max = 300.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(30.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant),
                    )

                    if (viewModel.bookAnalysisResult != null) {
                        IconButton(
                            onClick = { viewModel.resetAnalyzeResult() },
                            modifier = Modifier.padding(8.dp),
                            colors =
                                IconButtonDefaults.iconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.surface,
                                ),
                        ) {
                            Icon(
                                imageVector = Icons.Default.Replay,
                                contentDescription = "Reset",
                                modifier = Modifier.padding(8.dp),
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (viewModel.bookAnalysisResult == null) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    PrimaryButton(text = "ライブラリから選択", onClick = {
                        imagePicker.launch("image/*")
                    }, fraction = 0.5f, enabled = !viewModel.isAnalyzing)
                    Spacer(modifier = Modifier.width(5.dp))
                    PrimaryButton(text = "カメラで撮影", onClick = { grantCameraPermission() }, enabled = !viewModel.isAnalyzing)
                }

                Spacer(modifier = Modifier.height(16.dp))

                PrimaryButton(text = "解析", onClick = {
                    viewModel.analyzeImage()
                }, enabled = viewModel.selectedImage != null, isLoading = viewModel.isAnalyzing)
            } else {
                OutlinedTextField(
                    label = "タイトル",
                    text = viewModel.title.toString(),
                    keyboardOptions =
                        KeyboardOptions(
                            imeAction = ImeAction.Next,
                        ),
                    enabled = !viewModel.isCreating,
                ) {
                    viewModel.title = it
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    label = "著者",
                    text = viewModel.author.toString(),
                    keyboardOptions =
                        KeyboardOptions(
                            imeAction = ImeAction.Next,
                        ),
                    enabled = !viewModel.isCreating,
                ) {
                    viewModel.author = it
                }

                Spacer(modifier = Modifier.height(16.dp))

                PickerMenu(
                    "カテゴリ",
                    options = BookCategory.entries.map { it.value }.toTypedArray(),
                    selectedOption = viewModel.category,
                    onOptionSelected = { viewModel.category = it },
                    optionLabel = { it },
                    enabled = !viewModel.isCreating,
                )
            }
        }
    }
}
