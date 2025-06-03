package com.huku.insighthub.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ServerException
import org.openapitools.client.models.AuthCredentials
import org.openapitools.client.models.User
import java.io.IOException

object AuthRepository {
    @Throws(
        IllegalStateException::class,
        IOException::class,
        UnsupportedOperationException::class,
        ClientException::class,
        ServerException::class,
    )
    suspend fun logIn(
        email: String,
        password: String,
    ): User =
        withContext(Dispatchers.IO) {
            OpenAPIClient.authApi.signIn(AuthCredentials(email, password))
        }

    @Throws(
        IllegalStateException::class,
        IOException::class,
        UnsupportedOperationException::class,
        ClientException::class,
        ServerException::class,
    )
    suspend fun signUp(
        email: String,
        password: String,
    ) = withContext(Dispatchers.IO) {
        OpenAPIClient.authApi.signUp(AuthCredentials(email, password))
    }

    @Throws(
        IllegalStateException::class,
        IOException::class,
        UnsupportedOperationException::class,
        ClientException::class,
        ServerException::class,
    )
    suspend fun getCurrentSession(): User =
        withContext(Dispatchers.IO) {
            OpenAPIClient.authApi.getSession()
        }

    @Throws(
        IllegalStateException::class,
        IOException::class,
        UnsupportedOperationException::class,
        ClientException::class,
        ServerException::class,
    )
    suspend fun logOut() {
        withContext(Dispatchers.IO) {
            OpenAPIClient.authApi.signOut()
        }
    }
}
