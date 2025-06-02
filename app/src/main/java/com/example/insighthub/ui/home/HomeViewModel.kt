package com.example.insighthub.ui.home

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import com.example.insighthub.enum.Constant

class HomeViewModel {
    var isAddingSheetPresented by mutableStateOf(false)

    fun onTapAddBookButton() {
        isAddingSheetPresented = true
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
