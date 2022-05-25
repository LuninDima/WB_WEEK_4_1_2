package com.example.wb_week_4_1_2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wb_week_4_1_2.model.Chat
import com.example.wb_week_4_1_2.model.RepositoryImpl
import com.example.wb_week_4_1_2.model.Repository

class MainViewModel(
    private val MainLiveDataObserver: MutableLiveData<AppStateListChats> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) : ViewModel() {
    fun getLiveData() = MainLiveDataObserver

    fun updateChat(chat: Chat){
        repositoryImpl.updateChat(chat)
    }

    fun addChat(chat: Chat){
        repositoryImpl.addChat(chat)
    }


    fun getChats() {
        MainLiveDataObserver.value = AppStateListChats.Loading
        Thread {
            MainLiveDataObserver.postValue(
                AppStateListChats.Success(
                    repositoryImpl.getChats()
                )
            )
        }.start()

    }
}