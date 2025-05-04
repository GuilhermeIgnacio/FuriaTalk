package com.guilherme.furiatalk.chat.domain

import com.guilherme.sqldelight.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    suspend fun fetchMessages(): Flow<List<Message>>
    suspend fun sendMessage(userMessage: String)
}