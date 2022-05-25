package com.example.wb_week_4_1_2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wb_week_4_1_2.model.Repository
import com.example.wb_week_4_1_2.model.RepositoryImpl

class DetailsViewModel(
    private val detailsLiveDataObserver: MutableLiveData<AppStateListChatTexts> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveData() = detailsLiveDataObserver

    fun getChatTexts() {
        detailsLiveDataObserver.value = AppStateListChatTexts.Loading
        Thread {
            Thread.sleep(0)
            detailsLiveDataObserver.postValue(
                AppStateListChatTexts.Success(
                    repositoryImpl.getChatTexts()
                )
            )
        }.start()

    }
}