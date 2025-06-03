package com.huku.insighthub.repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import org.openapitools.client.models.User

object SessionRepository {
    var session: User? = null
        private set

    fun setSession(session: User?) {
        this.session = session
    }
}
