package com.example.myapplication22

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.runtime.*
import kotlinx.coroutines.*

@Composable
fun NewsApp(newsViewModel: NewsViewModel) {
    val coroutineScope = rememberCoroutineScope()
    var currentNews by remember { mutableStateOf(newsViewModel.currentNews) }

    LaunchedEffect(newsViewModel) {
        // Запускаем корутину для обновления новостей каждые 5 секунд
        coroutineScope.launch {
            while (isActive) {
                delay(5000L) // Задержка в 5 секунд
                newsViewModel.changeCurrentNews()
                currentNews = newsViewModel.currentNews
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        val newsList = newsViewModel.newsList
        for (news in newsList) {
            NewsQuarter(
                newsTitle = news.title,
                likes = news.likes,
                onLikeClicked = { newsViewModel.onLikeClicked(news) }
            )
        }
    }
}
