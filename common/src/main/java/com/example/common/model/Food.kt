package com.example.common.model

import java.io.Serializable

data class Food(var id: Long, var name: String, var price: Float, var num: Int, var imgUrl: String, var type: Int): Serializable {
    constructor() : this(1, "default", 18.5f, 0, "http://xilelqs.top/img/yxrs.png", 0)
}