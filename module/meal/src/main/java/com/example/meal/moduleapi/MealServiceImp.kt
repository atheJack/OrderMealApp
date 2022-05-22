package com.example.meal.moduleapi

import android.content.Context
import com.example.common.util.DataUtil
import com.example.meal.adapter.ShopCartFoodListAdapter
import com.example.common.model.Food
import com.example.meal_api.IMealService

object MealServiceImp: IMealService {
    override fun getAdapter(context: Context, list: List<Any>): Any {
        return ShopCartFoodListAdapter(context, list as List<Food>)
    }
}