package com.example.myapplication22

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NewsQuarter(newsTitle: String, likes: Int, onLikeClicked: () -> Unit) {
    var likesCount by remember { mutableStateOf(likes) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = newsTitle,
        )
        Button(
            onClick = {
                likesCount++
                onLikeClicked()
            },
            modifier = Modifier
                .width(100.dp)
                .padding(top = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors()
        ) {
            Text(text = "Likes: $likesCount")
        }
    }
}