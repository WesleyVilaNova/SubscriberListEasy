package com.example.subscriberlisteasy.repository

import androidx.lifecycle.LiveData
import com.example.subscriberlisteasy.data.db.dao.SubscriberDAO
import com.example.subscriberlisteasy.data.db.entity.SubscriberEntity

class DataBaseDataSource(private val subscriberDAO: SubscriberDAO) : ISubscriberRepository {
    override suspend fun insertSubscriber(name: String, email: String): Long {
        val subscriberInsert = SubscriberEntity(
            name = name,
            email = email
        )
        return subscriberDAO.insert(subscriberInsert)
    }

    override suspend fun updateSubscriber(id: Long, name: String, email: String) {
        val subscriberUpdate = SubscriberEntity(
            id = id,
            name = name,
            email = email
        )
        return subscriberDAO.update(subscriberUpdate)
    }

    override suspend fun deleteSubscriber(id: Long) {
        return subscriberDAO.delete(id)
    }

    override suspend fun deleteAllSubscriber() {
        subscriberDAO.getAll()
    }

    override suspend fun getAllSubscriber(): List<SubscriberEntity> {
        return subscriberDAO.getAll()
    }
}
