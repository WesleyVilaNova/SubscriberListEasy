package com.example.subscriberlisteasy.repository

import androidx.lifecycle.LiveData
import com.example.subscriberlisteasy.data.db.entity.SubscriberEntity

interface ISubscriberRepository {

    suspend fun insertSubscriber(name: String, email: String): Long

    suspend fun updateSubscriber(id: Long, name: String, email: String)

    suspend fun deleteSubscriber(id: Long)

    suspend fun deleteAllSubscriber()

    suspend fun getAllSubscriber(): List<SubscriberEntity>
}
