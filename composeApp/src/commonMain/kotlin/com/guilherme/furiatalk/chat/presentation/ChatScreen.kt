package com.guilherme.furiatalk.chat.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.guilherme.furiatalk.chat.presentation.components.ChatBox
import com.guilherme.furiatalk.chat.presentation.components.ChatItem

@Composable
fun ChatScreen(viewModel: ChatViewModel) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent

    val messages = state.messages
    Scaffold(
        containerColor = Color(0xff0b141a)
    ) { padding ->
        Column(modifier = Modifier.statusBarsPadding().navigationBarsPadding().imePadding()) {
            LazyColumn(
                modifier = Modifier.weight(1f),
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
