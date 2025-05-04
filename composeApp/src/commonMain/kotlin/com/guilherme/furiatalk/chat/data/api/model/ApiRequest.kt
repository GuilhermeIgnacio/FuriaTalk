package com.guilherme.furiatalk.chat.data.api.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiRequest(
    @SerialName("contents")
    val contents: List<Content>,
    @SerialName("system_instruction")
    val systemInstruction: SystemInstruction
)

@Serializable
data class Content(
    @SerialName("parts")
    val parts: List<Part>
)

@Serializable
data class Part(
    @SerialName("text")
    val text: String
)

@Serializable
data class SystemInstruction(
    @SerialName("parts")
    val parts: List<Part>
)