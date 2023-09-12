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
        News("Заголовок 1", "Содержание новости 1", 0, false),
        News("Заголовок 2", "Содержание новости 2", 0, false),
        News("Заголовок 3", "Содержание новости 3", 0, false),
        News("Заголовок 4", "Содержание новости 4", 0, false),
        News("Заголовок 5", "Содержание новости 5", 0, false),
        News("Заголовок 6", "Содержание новости 6", 0,false),
        News("Заголовок 7", "Содержание новости 7", 0,false),
        News("Заголовок 8", "Содержание новости 8", 0,false),
        News("Заголовок 9", "Содержание новости 9", 0,false),
        News("Заголовок 10", "Содержание новости 10", 0,false),
    )

    private val _newsState = MutableStateFlow(getRandomNewsSubset())
    val newsState: StateFlow<List<News>> = _newsState

    init {
        startUpdatingNews()
    }

    fun onLikeClick(news: News) {
        val index = newsList.indexOf(news)
        if (index != -1 && !newsList[index].likedByUser) {
            newsList[index].likes++
            newsList[index].likedByUser = true // Устанавливаем флаг, что пользователь поставил лайк
            // Обновляем состояние только среди отображаемых новостей
            val updatedSubset = _newsState.value.map { if (it == news) newsList[index] else it }
            _newsState.value = updatedSubset
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
        val visibleNews = _newsState.value
        val remainingNews = newsList.filterNot { it in visibleNews }

        if (remainingNews.isNotEmpty()) {
            val randomVisibleIndex = Random.nextInt(visibleNews.size)
            val randomRemainingIndex = Random.nextInt(remainingNews.size)

            val updatedVisibleList = visibleNews.toMutableList()
            updatedVisibleList[randomVisibleIndex] = remainingNews[randomRemainingIndex]

            _newsState.value = updatedVisibleList
        }
    }


    private fun getRandomNewsSubset(): List<News> {
        return newsList.shuffled().take(4) // Выбираем случайные 4 новости из списка
    }
}
