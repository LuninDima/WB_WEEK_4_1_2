package com.example.wb_week_4_1_2.model

class RepositoryImpl : Repository {
    override fun updateChat(chat: Chat) {
        listChat[0] = chat
    }
    override fun getChats(): List<Chat> = getListChats()
    override fun getChatTexts(): List<ChatTexts> = getListChatTexts()
    override fun addChat(chat:Chat) {
        listChat.add(chat)
    }

}