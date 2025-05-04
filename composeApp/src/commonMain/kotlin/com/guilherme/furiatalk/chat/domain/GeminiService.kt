package com.guilherme.furiatalk.chat.domain

import com.guilherme.furiatalk.chat.data.api.model.ApiResponse
import com.guilherme.furiatalk.core.domain.GeminiError
import com.guilherme.furiatalk.core.domain.Result

interface GeminiService {

    suspend fun generateMessage(userMessage: String): Result<ApiResponse, GeminiError.Response>

}