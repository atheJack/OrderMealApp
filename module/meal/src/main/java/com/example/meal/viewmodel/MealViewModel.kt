package com.example.meal.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.common.BaseViewModel
import com.example.common.network.CommonResponse
import com.example.common.model.Food
import com.example.common.network.NetworkManager
import com.example.meal.repo.MealApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel: BaseViewModel() {

    var foodData: MutableLiveData<CommonResponse<List<Food>>> = MutableLiveData()
    var totalPrice: MutableLiveData<Float> = MutableLiveData(0f)
    var totalNum: MutableLiveData<Int> = MutableLiveData(0)
    var shopCartFoodData: MutableLiveData<ArrayList<Food>> = MutableLiveData(ArrayList())
    private val api = NetworkManager.getApi(MealApi::class.java)


    fun getFoodList() {
        api.getFoodList().enqueue(object : Callback<CommonResponse<List<Food>>> {
            override fun onResponse(
                call: Call<CommonResponse<List<Food>>>?,
                response: Response<CommonResponse<List<Food>>>?
            ) {
                foodData.postValue(response?.body())
            }

            override fun onFailure(call: Call<CommonResponse<List<Food>>>?, t: Throwable?) {

            }
        })
    }


    fun addFood(food: Food) {
        val currPrice = totalPrice.value
        currPrice?.let {
            totalPrice.postValue(currPrice + food.price)
        }
        val currNum = totalNum.value
        if (currNum != null) {
            totalNum.postValue(currNum + 1)
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
        val currPrice = totalPrice.value
        currPrice?.let {
            totalPrice.postValue(currPrice - food.price)
        }
        val currNum = totalNum.value
        if (currNum != null) {
            totalNum.postValue(currNum + 1)
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