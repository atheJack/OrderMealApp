package com.example.common.database

import android.content.Context
import androidx.room.Room

class DbManager {

    fun getDb(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "test_database"
        ).build()
    }

    companion object{
        private val sInstance = DbManager()
        fun getInstance() = sInstance
    }
}