package com.example.meal.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.common.BaseViewModel
import com.example.common.network.CommonResponse
import com.example.meal.model.Food

class MealViewModel: BaseViewModel() {

    var foodData: MutableLiveData<CommonResponse<List<Food>>> = MutableLiveData()
    var totalPrice: MutableLiveData<Float> = MutableLiveData(0f)
    var shopCartFoodData: MutableLiveData<ArrayList<Food>> = MutableLiveData(ArrayList())

    fun getFoodList() {
        //假数据
        val data = ArrayList<Food>().apply {
            for (i in 0..10) {
                val food = Food()
                if (i % 2 == 0) {
                    food.name = "鱼香肉丝"
                } else {
                    food.name = "宫保鸡丁"
                }
                add(food)
            }
        }
        val response = CommonResponse<List<Food>>(200, "", data)
        foodData.postValue(response)
    }

    fun addFood(food: Food) {
        val currNum = totalPrice.value
        currNum?.let {
            totalPrice.postValue(currNum + food.price)
        }
        val list = shopCartFoodData.value
        list?.apply {
            if(!contains(food)) {
                add(food)
            }
            shopCartFoodData.value = list
        }
    }

    fun reduceFood(food: Food) {
        val currNum = totalPrice.value
        currNum?.let {
            totalPrice.postValue(currNum - food.price)
        }
        val list = shopCartFoodData.value
        list?.apply {
            if(food.num == 0) {
                remove(food)
            }
            shopCartFoodData.value = list
        }
    }

}