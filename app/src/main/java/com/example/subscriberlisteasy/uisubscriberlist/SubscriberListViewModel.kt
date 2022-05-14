package com.example.subscriberlisteasy.uisubscriberlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.subscriberlisteasy.data.db.entity.SubscriberEntity
import com.example.subscriberlisteasy.repository.ISubscriberRepository
import kotlinx.coroutines.launch

class SubscriberListViewModel(private val repository: ISubscriberRepository) : ViewModel() {

    private val _allSubscribersEvent = MutableLiveData<List<SubscriberEntity>>()
    val allSubscribersEvent: LiveData<List<SubscriberEntity>> get() = _allSubscribersEvent

    fun getSubscriber() = viewModelScope.launch {
        _allSubscribersEvent.postValue(repository.getAllSubscriber())
    }
}
