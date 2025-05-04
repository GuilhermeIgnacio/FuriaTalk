package com.guilherme.furiatalk

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.guilherme.furiatalk.chat.presentation.ChatScreen
import com.guilherme.furiatalk.chat.presentation.ChatViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = koinViewModel<ChatViewModel>()
        ChatScreen(viewModel = viewModel)
    }
}