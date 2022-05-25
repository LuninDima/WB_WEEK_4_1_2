package com.example.wb_week_4_1_2.model

import android.os.Parcelable
import com.example.wb_week_4_1_2.R
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random


@Parcelize
data class Chat(val id: Int, var image: Int, var title: String, var chatTexts: List<ChatTexts>, var timeLastMessage: String, var countMessage: Int) : Parcelable
var listChat = arrayListOf<Chat>()

    fun getListChats(): List<Chat>{
    for (i in 1..10){
       var random = Random.nextInt(100)
        listChat.add(Chat(i, R.drawable.image, "Заголовок чата $i", getListChatTexts(), "12:06", random))
    }
    return listChat
}


