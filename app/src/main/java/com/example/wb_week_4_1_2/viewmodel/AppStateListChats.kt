package com.example.wb_week_4_1_2.viewmodel

import com.example.wb_week_4_1_2.model.Chat

sealed class AppStateListChats {
    data class Success(val dataChats: List<Chat>) : AppStateListChats()
    data class Error(val error: Throwable) : AppStateListChats()
    object Loading : AppStateListChats()
}


