package com.guilherme.furiatalk.chat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Composable for input field
@Composable
fun ChatBox(onSend: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Type a message") },
            modifier = Modifier
                .weight(1f)
                .background(Color.White, RoundedCornerShape(24.dp)),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFF1E1E1E),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = Color.White,
                placeholderColor = Color.White
            )
        )
        IconButton(onClick = {
            if (text.isNotEmpty()) {
                onSend(text)
                text = ""
            }
        }) {
            Icon(Icons.Default.Send, contentDescription = "Send", tint = Color(0xFF075E54))
        }
    }
}