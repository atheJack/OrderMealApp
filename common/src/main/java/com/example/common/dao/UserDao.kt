package com.example.common.dao

import androidx.room.Dao
import androidx.room.Index
import androidx.room.Insert
import androidx.room.Query
import com.example.common.model.User

@Dao
interface UserDao {

    @Query("select * from user")
    fun getAll(): List<User>

    @Query("delete from user")
    fun deleteAll()

    @Insert
    fun insert(user: User)
}