package com.example.myapplication22

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class News(
    val title: String,
    val content: String,
    var likes: MutableState<Int> = mutableStateOf(0),
    var likedByUser: Boolean = false
)

