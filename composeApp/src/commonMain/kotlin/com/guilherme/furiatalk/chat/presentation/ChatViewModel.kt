package com.guilherme.furiatalk.chat.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// ViewModel to manage chat state
class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    fun sendMessage(text: String) {
        val newMessage = Message(text = text, isFromMe = true)
        _messages.value = _messages.value + newMessage
    }

    init {
        // Simulate initial messages
        _messages.value = listOf(
            Message("Hello!", isFromMe = false),
            Message("Hi there!", isFromMe = true)
        )
    }
}
