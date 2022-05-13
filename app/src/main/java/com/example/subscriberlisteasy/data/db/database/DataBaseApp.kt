package com.example.subscriberlisteasy.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.subscriberlisteasy.data.db.dao.SubscriberDAO
import com.example.subscriberlisteasy.data.db.entity.SubscriberEntity

@Database(entities = [SubscriberEntity::class], version = 1)
abstract class DataBaseApp : RoomDatabase() {

    abstract val subscriberDAO: SubscriberDAO

    companion object {
        private var INSTANCE: DataBaseApp? = null

        fun getInstance(context: Context): DataBaseApp {
            synchronized(this) {
                var instance: DataBaseApp? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        DataBaseApp::class.java,
                        "dataBase"
                    ).build()
                }
                return instance
            }
        }
    }
}
