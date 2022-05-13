package com.example.subscriberlisteasy.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Se não passar o nome da tabela o nome automaticamente fica o nome da classe
@Entity(tableName = "subscriberListEasy")
data class SubscriberEntity(
    @PrimaryKey(autoGenerate = true) // Dizendo que ao inserir um novo dado automaticamente vai ser incremetado
    val id: Long = 0,
    val name: String,
    val email: String
)
