package com.example.wb_week_4_1_2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlin.random.Random
import kotlin.random.nextInt

@Parcelize
data class ChatTexts(val id: Int, var text: String, var timeMessage: String) : Parcelable

fun getListChatTexts(): List<ChatTexts>{

    var listChatTexts = arrayListOf<ChatTexts>()

    for (i in 1..51){
        var random = Random.nextInt(100)
        listChatTexts.add(ChatTexts(1, "рандомный текст $random", "12:00"))
    }

    return listChatTexts
}
