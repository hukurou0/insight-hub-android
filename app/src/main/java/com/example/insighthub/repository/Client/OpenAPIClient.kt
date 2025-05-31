package com.example.insighthub.repository

import com.example.insighthub.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.openapitools.client.apis.AuthApi
import org.openapitools.client.apis.BookAnalysisApi
import org.openapitools.client.apis.BooksApi
import org.openapitools.client.infrastructure.ApiClient
import java.util.concurrent.TimeUnit

object OpenAPIClient {
    private const val PRODUCTION_URL = "https://mny9hjtbri.ap-northeast-1.awsapprunner.com"
    private const val DEBUG_URL = "http://0.0.0.0:8000"
    private val client: ApiClient

    private val baseUrl: String
        get() = if (!BuildConfig.DEBUG) DEBUG_URL else PRODUCTION_URL

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient
            .Builder()
            .apply {
                if (!BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        },
                    )
                }
                connectTimeout(60, TimeUnit.SECONDS)
                readTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)
            }.build()
    }

    init {
        client = ApiClient(baseUrl = baseUrl, client = httpClient)
    }

    // API client instances
    val booksApi: BooksApi by lazy { BooksApi() }
    val authApi: AuthApi by lazy { AuthApi() }
    val bookAnalysisApi: BookAnalysisApi by lazy { BookAnalysisApi() }

//    // Helper function to add auth token
//    fun setAuthToken(token: String) {
//        client.accessToken = token
//    }
//
//    // Helper function to clear auth token
//    fun clearAuthToken() {
//        client.accessToken = null
//    }
}
