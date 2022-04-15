package com.example.meal.model

data class Food(var id: Long, var name: String, var price: Float, var num: Int) {
    constructor() : this(1, "default", 18.5f, 0)
}