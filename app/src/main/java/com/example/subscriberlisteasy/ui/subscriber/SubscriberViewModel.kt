package com.example.subscriberlisteasy.ui.subscriber

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.subscriberlisteasy.R
import com.example.subscriberlisteasy.repository.ISubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: ISubscriberRepository) : ViewModel() {

    private val _subscriberStateEventData = MutableLiveData<SubscriberState>()
    val subscriberStateEventData: LiveData<SubscriberState>
        get() = _subscriberStateEventData

    private val _messageEventData = MutableLiveData<String>()
    val messageEventData: LiveData<String>
        get() = _messageEventData

    fun addOrUpdateSubscriber(name: String, email: String, id: Long) = viewModelScope.launch {
        if (id > 0) {
            updateSubscriber(id, name, email)
        } else {
            insertSubscriber(name, email)
        }
    }

    private fun updateSubscriber(id: Long, name: String, email: String) = viewModelScope.launch {
        try {
            repository.updateSubscriber(id, name, email)
            _subscriberStateEventData.value = SubscriberState.Update
            _messageEventData.value = R.string.subscriber_update_successfully.toString()
        } catch (e: Exception) {
            _messageEventData.value = R.string.subscriber_error_to_update.toString()
        }
    }

    private fun insertSubscriber(name: String, email: String) = viewModelScope.launch {
        try {
            val id = repository.insertSubscriber(name, email)
            if (id > 0) {
                _subscriberStateEventData.value = SubscriberState.Inserted
                _messageEventData.value = R.string.subscriber_inserted_successfully.toString()
            }
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    fun removeSubscriber(id: Long) = viewModelScope.launch {
        try {
            if (id == id) {
                repository.deleteSubscriber(id)
                _subscriberStateEventData.value = SubscriberState.Deleted
                _messageEventData.value = R.string.subscriber_delete_successfully.toString()
            }
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    sealed class SubscriberState {
        object Inserted : SubscriberState()
        object Update : SubscriberState()
        object Deleted : SubscriberState()
    }

    companion object {
        private val TAG = SubscriberViewModel::class.java.simpleName
    }
}
