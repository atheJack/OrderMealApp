package com.example.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.common.dao.UserDao
import com.example.common.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}