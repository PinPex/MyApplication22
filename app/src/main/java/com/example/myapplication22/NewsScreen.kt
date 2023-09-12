package com.example.myapplication22

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewsScreen(viewModel: NewsViewModel = viewModel()) {
    val newsState = viewModel.newsState.collectAsState(initial = emptyList())
    val newsList = newsState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        for (news in newsList) {
            NewsItem(news = news, onLikeClick = { viewModel.onLikeClick(news) })
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun NewsItem(news: News, onLikeClick: () -> Unit) {
    var liked by remember { mutableStateOf(false) }

    Column {
        Text(text = news.title)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = news.content)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${news.likes} Likes",
                modifier = Modifier
                    .weight(0.9f)
                    .padding(start = 4.dp)
            )
            IconButton(
                onClick = {
                    onLikeClick()
                    liked = true
                },
                modifier = Modifier
                    .weight(0.1f)
                    .padding(end = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Like"
                )
            }
        }
    }
}
