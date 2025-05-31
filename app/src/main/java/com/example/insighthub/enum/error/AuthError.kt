package com.example.insighthub.enum.error

sealed class AuthError : Exception() {
    object NoSessionFound : AuthError()
}
