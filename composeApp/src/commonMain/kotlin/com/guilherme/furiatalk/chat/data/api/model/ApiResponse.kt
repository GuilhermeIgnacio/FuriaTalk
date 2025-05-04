package com.guilherme.furiatalk.chat.data.api.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerialName("candidates")
    val candidates: List<Candidate>,
    @SerialName("modelVersion")
    val modelVersion: String,
    @SerialName("usageMetadata")
    val usageMetadata: UsageMetadata
)

@Serializable
data class Candidate(
    @SerialName("avgLogprobs")
    val avgLogprobs: Double,
    @SerialName("content")
    val content: Content,
    @SerialName("finishReason")
    val finishReason: String
)

@Serializable
data class CandidatesTokensDetail(
    @SerialName("modality")
    val modality: String,
    @SerialName("tokenCount")
    val tokenCount: Int
)

@Serializable
data class PromptTokensDetail(
    @SerialName("modality")
    val modality: String,
    @SerialName("tokenCount")
    val tokenCount: Int
)

@Serializable
data class UsageMetadata(
    @SerialName("candidatesTokenCount")
    val candidatesTokenCount: Int,
    @SerialName("candidatesTokensDetails")
    val candidatesTokensDetails: List<CandidatesTokensDetail>,
    @SerialName("promptTokenCount")
    val promptTokenCount: Int,
    @SerialName("promptTokensDetails")
    val promptTokensDetails: List<PromptTokensDetail>,
    @SerialName("totalTokenCount")
    val totalTokenCount: Int
)