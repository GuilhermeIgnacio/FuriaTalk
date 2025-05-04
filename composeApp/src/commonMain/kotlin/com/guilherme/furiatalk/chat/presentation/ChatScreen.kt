package com.guilherme.furiatalk.chat.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.guilherme.furiatalk.chat.presentation.components.ChatBox
import com.guilherme.furiatalk.chat.presentation.components.ChatItem


// Data class for messages
data class Message(val text: String, val isFromMe: Boolean)

@Composable
fun ChatScreen(viewModel: ChatViewModel) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent

    val messages = state.messages
    Scaffold(
        backgroundColor = Color(0xff0b141a)
    ) { padding ->
        Column(modifier = Modifier.padding(padding).navigationBarsPadding()) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                reverseLayout = true // New messages at the bottom
            ) {
                items(messages) { message ->
                    ChatItem(message = message)
                }
            }
            ChatBox(
                state = state,
                onEvent = onEvent
            )
        }
    }
}

// Theme for WhatsApp look
@Composable
fun WhatsAppCloneTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(
            primary = Color(0xFF075E54),
            background = Color(0xFFE5DDD5)
        ),
        content = content
    )
}