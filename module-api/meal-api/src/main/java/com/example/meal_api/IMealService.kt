package com.example.meal_api

import android.content.Context

interface IMealService {
    fun getAdapter(context: Context, list: List<Any>): Any
    fun getFoodObject(): Any
    fun getFoodList(): List<Any>
}