package com.example.insighthub.usecase

import com.example.insighthub.enum.BookCategory
import com.example.insighthub.repository.BookRepository
import com.example.insighthub.repository.SessionRepository
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ServerException
import org.openapitools.client.models.BookAnalysisResult
import org.openapitools.client.models.BookModel
import java.io.File
import java.io.IOException

object BookUseCase {
    @Throws(
        IllegalStateException::class,
        IOException::class,
        UnsupportedOperationException::class,
        ClientException::class,
        ServerException::class,
    )
    suspend fun create(
        userId: String,
        title: String,
        author: String,
        category: BookCategory,
        coverImageURL: String?,
    ): BookModel {
        val session = SessionRepository.session
        if (session == null) {
            throw IllegalStateException("Session is not available")
        }
        return BookRepository.create(userId, title, author, category.value, coverImageURL)
    }

    @Throws(
        IllegalStateException::class,
        IOException::class,
        UnsupportedOperationException::class,
        ClientException::class,
        ServerException::class,
    )
    suspend fun uploadImage(imageData: File): org.openapitools.client.models.UploadBookImage200Response =
        BookRepository.uploadImage(imageData)

    @Throws(
        IllegalStateException::class,
        IOException::class,
        UnsupportedOperationException::class,
        ClientException::class,
        ServerException::class,
    )
    suspend fun analyze(coverImage: File): BookAnalysisResult = BookRepository.analyze(coverImage)
}
