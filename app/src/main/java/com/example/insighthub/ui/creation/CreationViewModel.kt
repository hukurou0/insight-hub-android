package com.example.insighthub.ui.creation

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.insighthub.usecase.AuthUseCase
import com.example.insighthub.usecase.BookUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.openapitools.client.models.BookAnalysisResult
import org.openapitools.client.models.BookModel
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class CreationViewModel(
    val onDismiss: (BookModel?) -> Unit,
) : ViewModel() {
    var selectedImage by mutableStateOf<File?>(null)
    var takenPhotoUri by mutableStateOf<Uri?>(null)
    var bookAnalysisResult by mutableStateOf<BookAnalysisResult?>(null)
    var title by mutableStateOf<String?>(null)
    var author by mutableStateOf<String?>(null)
    var category by mutableStateOf<String?>(null)
    var isAnalyzing by mutableStateOf(false)
    var isCreating by mutableStateOf(false)

    fun selectImage(
        context: Context,
        uri: Uri,
    ) {
        val file = copyUriToFile(context, uri)
        selectedImage = file
    }

    fun resetAnalyzeResult() {
        bookAnalysisResult = null
    }

    fun analyzeImage() {
        CoroutineScope(Dispatchers.Main).launch {
            isAnalyzing = true
            try {
                val result = BookUseCase.analyze(selectedImage!!)
                title = result.title
                author = result.author
                category = result.category
                bookAnalysisResult = result
            } catch (error: Exception) {
                print(error)
            } finally {
                isAnalyzing = false
            }
        }
    }

    fun createBook() {
        CoroutineScope(Dispatchers.Main).launch {
            isCreating = true
            try {
                val user = AuthUseCase.getUser()
                if (user == null || selectedImage == null || title == null || author == null || category == null) {
                    return@launch
                }
                val image = BookUseCase.uploadImage(selectedImage!!)
                val result = BookUseCase.create(userId = user.id, title!!, author!!, category!!, image.url)
                onDismiss(result)
            } catch (error: Exception) {
                print(error)
            } finally {
                isCreating = false
            }
        }
    }

    private fun copyUriToFile(
        context: Context,
        uri: Uri,
    ): File? =
        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val file = File.createTempFile("picked_image_", ".jpg", context.cacheDir)
            val outputStream = FileOutputStream(file)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
}
