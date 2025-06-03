package com.huku.insighthub.enum.error

sealed class AuthError : Exception() {
    object NoSessionFound : AuthError()
}
