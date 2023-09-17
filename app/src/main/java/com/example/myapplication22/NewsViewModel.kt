package com.example.myapplication22

import android.os.Debug
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.concurrent.thread
import kotlin.random.Random
class NewsViewModel : ViewModel() {
    private val newsList = mutableListOf(
        News("Новость 1", "Lorem ipsum", 0, false),
        News("Новость 2", "Lorem ipsum", 0, false),
        News("Новость 3", "Lorem ipsum", 0, false),
        News("Новость 4", "Lorem ipsum", 0, false),
        News("Новость 5", "Lorem ipsum", 0, false),
        News("Новость 6", "Lorem ipsum", 0,false),
        News("Новость 7", "Lorem ipsum", 0,false),
        News("Новость 8", "Lorem ipsum", 0,false),
        News("Новость 9", "Lorem ipsum", 0,false),
        News("Новость 10", "Lorem ipsum", 0,false),
    )
    private val _newsState = MutableStateFlow(getRandomNewsSubset())
    val newsState: StateFlow<List<News>> = _newsState

    init {
        startRefreshingNews()
    }

    fun likeClick(news: News) {
        val index = newsList.indexOf(news)
        if (index != -1 && !newsList[index].likedByUser) {
            newsList[index].likes++
            newsList[index].likedByUser = true // Устанавливаем флаг, что пользователь поставил лайк

            replaceCurrentNews(_newsState.value.indexOf(newsList[index]), index)
        }
    }

    private fun startRefreshingNews() {
        viewModelScope.launch {
            while (true) {
                delay(5000)
                replaceRandomNews()
            }
        }
    }

    private fun replaceRandomNews() {
        val inVisibleListNews = _newsState.value
        val remainNews = newsList.filterNot { it in inVisibleListNews }

        if (remainNews.isNotEmpty()) {
            val randInVisibleListIndex = Random.nextInt(inVisibleListNews.size)
            val randRemainIndex = Random.nextInt(remainNews.size)
            replaceCurrentNews(randInVisibleListIndex, newsList.indexOf(remainNews[randRemainIndex]))
        }
    }

    private fun replaceCurrentNews(visibleListIndex: Int, newsListIndex: Int) {
        val updatedVisibleList = _newsState.value.toMutableList()
        updatedVisibleList[visibleListIndex] = newsList[newsListIndex]
        _newsState.value = updatedVisibleList.toList()
    }

    private fun getRandomNewsSubset(): List<News> {
        return newsList.shuffled().take(4)
    }
}