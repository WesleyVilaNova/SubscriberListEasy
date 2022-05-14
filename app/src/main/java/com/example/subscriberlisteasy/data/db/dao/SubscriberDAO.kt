package com.example.subscriberlisteasy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.subscriberlisteasy.data.db.entity.SubscriberEntity

@Dao
interface SubscriberDAO {
    @Insert
    suspend fun insert(subscriber: SubscriberEntity): Long

    @Update
    suspend fun update(subscriber: SubscriberEntity)

    @Query("DELETE FROM subscriberListEasy WHERE id = :id") // subscriberListEasy é o nome da tabela do banco de dados
    fun delete(id: Long)

    @Query("DELETE FROM subscriberListEasy")
    fun delete()

    @Query("SELECT * FROM subscriberListEasy")
    suspend fun getAll(): List<SubscriberEntity>
}
