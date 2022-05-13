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

    fun addSubscriber(name: String, email: String) = viewModelScope.launch {
        try {
            val id = repository.insertSubscriber(name, email)
            if (id > 0) {
                _subscriberStateEventData.postValue(SubscriberState.Inserted)
                _messageEventData.value = R.string.subscriber_inserted_successfully.toString()
                Log.i(TAG, "id menor que 0")
            }
        } catch (e: Exception) {
            _messageEventData.value = R.string.subscriber_error_to_insert.toString()
            Log.e(TAG, e.toString())
        }
    }

    sealed class SubscriberState {
        object Inserted : SubscriberState()
    }

    companion object {
        private val TAG = SubscriberViewModel::class.java.simpleName
    }
}
