package com.example.myapplication22

import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {
    val newsList = listOf(
        News("2DROTS выиграли FC10", 0),
        News("Николай Соболев вернулся в США", 2),
        News("Spirit собрали новый состав", 3),
        News("Нави терпят поражение", 4),
        News("Когда же игроки вп заберут трофей?", 5)
    )

    var currentNewsIndex = 0

    fun onLikeClicked(index: Int) {
        newsList[index].likes++
    }
}