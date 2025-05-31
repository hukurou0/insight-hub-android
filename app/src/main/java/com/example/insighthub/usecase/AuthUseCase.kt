package com.example.insighthub.usecase

import com.example.insighthub.repository.AuthRepository
import com.example.insighthub.repository.SessionRepository
import org.openapitools.client.models.User

object AuthUseCase {
    @Throws(Exception::class)
    suspend fun logIn(
        email: String,
        password: String,
    ) {
        val session = AuthRepository.logIn(email, password)
        SessionRepository.setSession(session)
    }

    @Throws(Exception::class)
    suspend fun signUp(
        email: String,
        password: String,
    ) {
        AuthRepository.signUp(email, password)
        val session = AuthRepository.getCurrentSession()
        SessionRepository.setSession(session)
    }

    suspend fun getUser(): User? =
        try {
            AuthRepository.getCurrentSession()
        } catch (e: Exception) {
            null
        }

    suspend fun isLoggedIn(): Boolean =
        try {
            val session = AuthRepository.getCurrentSession()
            SessionRepository.setSession(session)
            true
        } catch (e: Exception) {
            false
        }

    @Throws(Exception::class)
    suspend fun logOut() {
        AuthRepository.logOut()
        SessionRepository.setSession(null)
    }
}
