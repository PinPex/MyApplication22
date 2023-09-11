package com.example.myapplication22

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
class NewsViewModel: ViewModel() {
    val newsList = listOf(
        News("Новость 1", 0),
        News("Новость 2", 0),
        News("Новость 3", 0),
        News("Новость 4", 0),
        News("Новость 5", 0),
        News("Новость 6", 0),
        News("Новость 7", 0),
    ).slice(1..5).toMutableList()

    private val currentNewsIndex = mutableStateOf(0)

    val currentNews: News
        get() = newsList[currentNewsIndex.value]
    fun onLikeClicked(news: News) {
        news.likes++
    }

    fun changeCurrentNews() {
        newsList.shuffle()
        currentNewsIndex.value = (currentNewsIndex.value + 1) % newsList.size
    }
}
