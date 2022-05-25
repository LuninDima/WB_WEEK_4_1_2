package com.example.wb_week_4_1_2.viewmodel

import com.example.wb_week_4_1_2.model.ChatTexts

sealed class AppStateListChatTexts  {
    data class Success(val dataChats: List<ChatTexts>) : AppStateListChatTexts()
    data class Error(val error: Throwable) : AppStateListChatTexts()
    object Loading : AppStateListChatTexts()
}

