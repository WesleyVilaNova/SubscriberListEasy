package com.example.subscriberlisteasy.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


// Se não passar o nome da tabela no @Entity, automaticamente fica o nome da classe
@Parcelize
@Entity(tableName = "subscriberListEasy")
data class SubscriberEntity(
    @PrimaryKey(autoGenerate = true) // Dizendo que ao inserir um novo dado automaticamente vai ser incremetado
    val id: Long = 0,
    val name: String,
    val email: String
) : Parcelable
