package com.guilherme.furiatalk.core.domain

interface GeminiError: Error {
    enum class Response: GeminiError {
        BAD_REQUEST,
        UNAUTHORIZED,
        SERVER_ERROR,
        SERVICE_UNAVAILABLE,
        UNKNOWN
    }
}