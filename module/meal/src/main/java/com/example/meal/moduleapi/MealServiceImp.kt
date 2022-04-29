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

    override fun getFoodObject(): Any {
        return Food()
    }

    override fun getFoodList(): List<Any> {
        val data = ArrayList<Food>().apply {
            for (i in 0 until DataUtil.nameList.size) {
                val food = Food()
                food.name = DataUtil.nameList[i]
                food.price = DataUtil.priceList[i]
                food.imgUrl = DataUtil.urlList[i]
                food.id = (i + 1).toLong()
                add(food)
            }
        }
        return data
    }
}