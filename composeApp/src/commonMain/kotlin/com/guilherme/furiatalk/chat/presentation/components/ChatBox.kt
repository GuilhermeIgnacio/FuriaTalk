package com.guilherme.furiatalk.chat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.guilherme.furiatalk.chat.presentation.ChatEvents
import com.guilherme.furiatalk.chat.presentation.ChatState

// Composable for input field
@Composable
fun ChatBox(
    state: ChatState,
    onEvent: (ChatEvents) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = state.chatTextField ?: "",
            enabled = !state.isLoading,
            onValueChange = { onEvent(ChatEvents.OnChatTextFieldValueChanged(it)) },
            placeholder = { Text("Mensagem") },
            modifier = Modifier
                .weight(1f)
                .background(Color.White, RoundedCornerShape(24.dp)),
            colors = TextFieldDefaults.colors().copy(
                unfocusedContainerColor = Color(0xFF1E1E1E),
                focusedContainerColor = Color(0xFF1E1E1E),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedPlaceholderColor =  Color(0xFF515151),
                focusedPlaceholderColor =  Color(0xFF515151)
            )
        )
        IconButton(
            onClick = { onEvent(ChatEvents.OnSubmitMessageButtonClicked) },
            colors = IconButtonDefaults.iconButtonColors().copy(
                containerColor = Color(0xFF075E54)
            )
        ) {
            Icon(Icons.Default.Send, contentDescription = "Send", tint = Color.Black)
        }
    }
}