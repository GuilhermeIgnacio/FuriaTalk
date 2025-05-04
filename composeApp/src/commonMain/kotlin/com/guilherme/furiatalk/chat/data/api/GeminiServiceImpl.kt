package com.guilherme.furiatalk.chat.data.api

import com.guilherme.furiatalk.chat.data.api.model.ApiRequest
import com.guilherme.furiatalk.chat.data.api.model.ApiResponse
import com.guilherme.furiatalk.chat.data.api.model.Content
import com.guilherme.furiatalk.chat.data.api.model.Part
import com.guilherme.furiatalk.chat.data.api.model.SystemInstruction
import com.guilherme.furiatalk.chat.domain.GeminiService
import com.guilherme.furiatalk.chat.presentation.Message
import com.guilherme.furiatalk.core.domain.GeminiError
import com.guilherme.furiatalk.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class GeminiServiceImpl : GeminiService {

    companion object {
        const val GEMINI_API_ENDPOINT =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="
        const val API_KEY = ""
    }

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun generateMessage(userMessage: String): Result<ApiResponse, GeminiError.Response> {

        val systemInstruction =
            "Você é um chatbot oficial da FURIA, uma organização brasileira de esports reconhecida internacionalmente. Sua missão é interagir com fãs, jogadores e curiosos de maneira simpática, informativa e alinhada com a identidade da FURIA: ousada, competitiva, inovadora e apaixonada por games. Responda sempre com energia, use uma linguagem jovem e acessível, e esteja pronto para falar sobre os times, jogos, eventos, produtos e tudo relacionado ao universo FURIA. Quando necessário, direcione os usuários para canais oficiais da organização. Nunca invente informações sobre resultados ou jogadores — se não souber, diga que vai verificar ou sugira consultar os canais oficiais."

        val requestBody = ApiRequest(
            systemInstruction = SystemInstruction(
                parts = listOf(Part(text = systemInstruction))
            ),
            contents = listOf(
                Content(
                    parts = listOf(Part(text = userMessage))
                )
            )
        )

        return try {

            val response = client.post(GEMINI_API_ENDPOINT + API_KEY) {
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }

            when(response.status) {
                HttpStatusCode.OK -> Result.Success(response.body())
                HttpStatusCode.BadRequest -> Result.Error(GeminiError.Response.BAD_REQUEST)
                HttpStatusCode.Unauthorized -> Result.Error(GeminiError.Response.UNAUTHORIZED)
                HttpStatusCode.InternalServerError -> Result.Error(GeminiError.Response.SERVER_ERROR)
                HttpStatusCode.ServiceUnavailable -> Result.Error(GeminiError.Response.SERVICE_UNAVAILABLE)
                else -> Result.Error(GeminiError.Response.UNKNOWN)
            }

        } catch (e: Exception) {

            val error = when(e) {
                else -> GeminiError.Response.UNKNOWN
            }

            e.printStackTrace()
            Result.Error(error)
        }

    }
}