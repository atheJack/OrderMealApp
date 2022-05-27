package com.example.common.model
import java.io.Serializable

data class Order(var id: Int = 0,
                 var username: String = "",
                 var state: Int = 1,
                 var totalPrice: Float = 11.0f,
                 var totalNum: Int = 20,
                 var date: Long = System.currentTimeMillis(),
                 var foodList: List<Food> = ArrayList()): Serializable
