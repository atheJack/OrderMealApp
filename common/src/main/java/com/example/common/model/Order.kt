package com.example.common.model
import java.io.Serializable

data class Order(var id: Int = 0,
                 var username: String = "",
                 var state: Int = 1, // 1表示待处理，2表示处理中，3表示已完成
                 var totalPrice: Float = 11.0f,
                 var totalNum: Int = 20,
                 var date: Long = System.currentTimeMillis(),
                 var foodList: List<Food> = ArrayList()): Serializable
