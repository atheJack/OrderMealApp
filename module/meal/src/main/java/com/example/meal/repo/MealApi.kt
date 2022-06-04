package com.example.meal.repo

import com.example.common.model.Food
import com.example.common.network.CommonResponse
import com.example.common.network.HostUtil
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {
    @GET(HostUtil.GET_FOOD)
    fun getFoodList(): Call<CommonResponse<List<Food>>>

    @GET(HostUtil.GET_FOOD_TYPE)
    fun getFoodTypeList(): Call<CommonResponse<List<Int>>>
}