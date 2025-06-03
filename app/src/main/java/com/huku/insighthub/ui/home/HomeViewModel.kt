package com.huku.insighthub.ui.home

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.huku.insighthub.enum.Constant
import org.openapitools.client.models.BookModel

class HomeViewModel : ViewModel() {
    var isAddingSheetPresented by mutableStateOf(false)
    var isSettingsSheetPresented by mutableStateOf(false)
    var addedBook by mutableStateOf<BookModel?>(null)

    fun onTapAddBookButton() {
        isAddingSheetPresented = true
    }

    fun onTapSettingsButton() {
        isSettingsSheetPresented = true
    }

    fun onTapOpenInWebButton(
        context: Context,
        book: BookModel,
    ) {
        val intent =
            Intent(
                Intent.ACTION_VIEW,
                Constant.URL
                    .Book(book.id)
                    .string
                    .toUri(),
            )
        context.startActivity(intent)
    }

    fun onTapOpenInWebButton(context: Context) {
        val intent =
            Intent(
                Intent.ACTION_VIEW,
                Constant.URL.Base.string
                    .toUri(),
            )
        context.startActivity(intent)
    }
}
