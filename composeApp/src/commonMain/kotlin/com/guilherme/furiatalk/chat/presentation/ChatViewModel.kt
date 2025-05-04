package com.guilherme.furiatalk.chat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilherme.furiatalk.chat.data.api.model.Message
import com.guilherme.furiatalk.chat.domain.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ChatState(
    val chatTextField: String? = null,
    val messages: List<Message> = emptyList()
)

sealed interface ChatEvents {
    data class OnChatTextFieldValueChanged(val value: String) : ChatEvents
    data object OnSubmitMessageButtonClicked : ChatEvents
}

class ChatViewModel(
    private val messageRepository: MessageRepository
) : ViewModel() {
    /*private val _messages = MutableStateFlow<List<Message>>(emptyList())
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
    }*/

    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            messageRepository.fetchMessages().collect { messages ->

                val messagesList = messages.map {
                    Message(
                        text = it.message,
                        isFromMe = it.isFromUser.toInt() == 1
                    )
                }

                _state.update { it.copy(messages = messagesList) }

            }
        }
    }

    fun onEvent(event: ChatEvents) {
        when (event) {
            is ChatEvents.OnChatTextFieldValueChanged -> {
                _state.update { it.copy(chatTextField = event.value) }
            }
            ChatEvents.OnSubmitMessageButtonClicked -> {

                val chatTextField = _state.value.chatTextField

                viewModelScope.launch {

                    if (!chatTextField.isNullOrEmpty()) {
                        messageRepository.sendMessage(chatTextField)
                    }

                }

            }
        }
    }

}
