package com.example.meal_api

import android.content.Context

interface MealService {
    fun getAdapter(context: Context, list: List<Any>): Any
    fun getFoodObject(): Any
}