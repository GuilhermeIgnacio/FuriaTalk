package com.guilherme.furiatalk.chat.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.guilherme.Database
import com.guilherme.furiatalk.chat.data.local.DriverFactory
import com.guilherme.furiatalk.chat.domain.GeminiService
import com.guilherme.furiatalk.chat.domain.MessageRepository
import com.guilherme.furiatalk.core.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class MessageRepositoryImpl(
    private val geminiService: GeminiService,
    private val driverFactory: DriverFactory
) : MessageRepository {

    val driver = driverFactory.createDriver()
    val database = Database(driver)
    val messageQueries = database.messageQueries

    override suspend fun fetchMessages(): Flow<List<com.guilherme.sqldelight.Message>> {
        val messages: Flow<List<com.guilherme.sqldelight.Message>> = messageQueries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO)

        return messages

    }

    override suspend fun sendMessage(userMessage: String) {

        when (val result = geminiService.generateMessage(userMessage)) {
            is Result.Success -> {

                database.transaction {
                    messageQueries.insert(message = userMessage, isFromUser = 1)

                    val message = result.data.candidates.get(0).content.parts[0].text

                    messageQueries.insert(message = message, isFromUser = 0)
                }
            }

            is Result.Error -> {
                database.transaction {
                    messageQueries.insert(message = userMessage, isFromUser = 1)

                    messageQueries.insert(
                        message = "Erro ao obter resposta. Por favor, envie sua mensagem novamente ou reinicie o aplicativo e tente novamente",
                        isFromUser = 0
                    )
                }
            }
        }

    }

}