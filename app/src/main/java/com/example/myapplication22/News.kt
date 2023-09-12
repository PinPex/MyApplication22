package com.example.myapplication22

data class News(
    val title: String,
    val content: String,
    var likes: Int = 0,
    var likedByUser: Boolean = false
)

