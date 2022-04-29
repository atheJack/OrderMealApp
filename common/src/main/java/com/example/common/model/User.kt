package com.example.common.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class User(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id:Long = 0,

    @ColumnInfo(name = "name")
    var name:String = "wch",

    @ColumnInfo(name = "password")
    var password:String = "",

    @ColumnInfo(name = "is_manager")
    var isManager:Boolean = false) {

    @Ignore
    constructor(): this(0)
}