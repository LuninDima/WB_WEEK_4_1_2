package com.example.wb_week_4_1_2.model

interface Repository {
    fun updateChat(chat: Chat)

    fun getChats(): List<Chat>

    fun getChatTexts():List<ChatTexts>

    fun addChat(chat:Chat)

}