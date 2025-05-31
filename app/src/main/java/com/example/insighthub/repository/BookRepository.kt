package com.example.insighthub.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ServerException
import org.openapitools.client.models.AnalyzeBookRequest
import org.openapitools.client.models.BookAnalysisResult
import org.openapitools.client.models.BookCreate
import org.openapitools.client.models.BookModel
import org.openapitools.client.models.UploadBookImage200Response
import java.io.File
import java.io.IOException
import java.time.OffsetDateTime
import java.util.Base64

object BookRepository {
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
        category: String,
        coverImageURL: String?,
    ): BookModel =
        withContext(Dispatchers.IO) {
            OpenAPIClient.booksApi.createBook(
                userId,
                BookCreate(
                    title = title,
                    author = author,
                    category = category,
                    coverImage = coverImageURL,
                    status = "未読",
                    notes = null,
                    lastReadDate = OffsetDateTime.now(),
                ),
            )
        }

    @Throws(
        IllegalStateException::class,
        IOException::class,
        UnsupportedOperationException::class,
        ClientException::class,
        ServerException::class,
    )
    suspend fun uploadImage(imageData: File): UploadBookImage200Response =
        withContext(Dispatchers.IO) {
            OpenAPIClient.booksApi.uploadBookImage(imageData)
        }

    @Throws(
        IllegalStateException::class,
        IOException::class,
        UnsupportedOperationException::class,
        ClientException::class,
        ServerException::class,
    )
    suspend fun analyze(coverImage: File): BookAnalysisResult =
        withContext(Dispatchers.IO) {
            val fileBytes = coverImage.readBytes()
            val base64EncodedImage = Base64.getEncoder().encodeToString(fileBytes)
            OpenAPIClient.bookAnalysisApi.analyzeBook(AnalyzeBookRequest(base64EncodedImage))
        }
}
