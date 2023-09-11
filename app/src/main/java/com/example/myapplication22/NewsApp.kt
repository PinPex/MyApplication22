package com.example.myapplication22

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication22.News

@Composable
fun NewsApp(newsViewModel: NewsViewModel) {
    val newsList = newsViewModel.newsList

    Column(modifier = Modifier.fillMaxSize()) {
        for (news in newsList) {
            NewsQuarter(
                newsTitle = news.title,
                likes = news.likes,
                onLikeClicked = { newsViewModel.onLikeClicked(1) }
            )
        }
    }
}
