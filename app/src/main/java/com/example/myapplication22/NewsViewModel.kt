package com.example.myapplication22

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class NewsViewModel : ViewModel() {
    private val newsList = mutableListOf(
        News("Заголовок 1", "Содержание новости 1", 0),
        News("Заголовок 2", "Содержание новости 2", 0),
        News("Заголовок 3", "Содержание новости 3", 0),
        News("Заголовок 4", "Содержание новости 4", 0),
        News("Заголовок 5", "Содержание новости 5", 0),
    )

    private val _newsState = MutableStateFlow(newsList)
    val newsState: StateFlow<List<News>> = _newsState

    init {
        startUpdatingNews()
    }

    fun onLikeClick(news: News) {
        val index = newsList.indexOf(news)
        if (index != -1) {
            newsList[index].likes++
            _newsState.value = newsList.toList().toMutableList()
        }
    }

    private fun startUpdatingNews() {
        GlobalScope.launch {
            while (true) {
                delay(5000)
                replaceRandomNews()
            }
        }
    }

    private fun replaceRandomNews() {
        val randomIndex = Random.nextInt(newsList.size)
        val updatedList = newsList.toMutableList()
        updatedList[randomIndex] = News("Новый заголовок", "Новое содержание", 0)
        _newsState.value = updatedList.toList().toMutableList()
    }
}
