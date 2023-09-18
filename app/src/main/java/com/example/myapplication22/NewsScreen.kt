package com.example.myapplication22


import android.util.DisplayMetrics
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun NewsScreen(viewModel: NewsViewModel = viewModel()) {
    val newsState = viewModel.newsState.collectAsState(initial = emptyList())
    val newsList = newsState.value.take(2)
    val newsList2 = newsState.value.drop(2)
    var count = 0;
    Column(){
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            for (news in newsList) {
                count++;
                if (count > 2) continue
                Column(
                    modifier = Modifier
                        .height(370.dp)
                        .width(165.dp)
                        .align(Alignment.CenterVertically)
                )
                {
                    NewsItem(news = news, onLikeClick = { viewModel.likeClick(news) })
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        }
        Row(
            modifier = Modifier
                .width(400.dp)
                .padding(18.dp)

        ) {
            for (news in newsList2) {
                count++;
                if (count > 2) {
                    Column(
                        modifier = Modifier
                            .height(370.dp)
                            .width(165.dp)
                            .align(Alignment.CenterVertically)

                    )
                    {
                        NewsItem(news = news, onLikeClick = { viewModel.likeClick(news) })
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                }
            }
        }
    }
}


@Composable
fun NewsItem(news: News, onLikeClick: () -> Unit) {
    var liked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {
        Text(
            text = news.title,
            color = Color.White,
            modifier = Modifier
                .background(Color.Black)
                .padding(8.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = news.content)
        Spacer(modifier = Modifier.weight(1f)) // Заполнитель, чтобы лайки были внизу

        // Разместите лайки внизу
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "${news.likes.value} Likes",
                    color = Color.White
                )
                IconButton(
                    onClick = {
                        onLikeClick()
                        liked = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Like"
                    )
                }
            }
        }
    }
}







