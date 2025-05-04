package com.guilherme.furiatalk.chat.data.repository

import com.guilherme.furiatalk.chat.domain.GeminiService
import com.guilherme.furiatalk.chat.domain.MessageRepository

class MessageRepositoryImpl(
    private val geminiService: GeminiService,
) : MessageRepository {



}