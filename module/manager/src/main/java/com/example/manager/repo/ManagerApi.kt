package com.example.manager.repo

import com.example.common.model.Food
import com.example.common.model.Order
import com.example.common.network.CommonResponse
import com.example.common.network.HostUtil
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ManagerApi {

    @GET(HostUtil.GET_FOOD)
    fun getFoodList(): Call<CommonResponse<List<Food>>>

    @GET(HostUtil.GET_FOOD_TYPE)
    fun getFoodTypeList(): Call<CommonResponse<List<Int>>>

    @POST(HostUtil.ADD_FOOD)
    fun addFood(@Body food: Food): Call<CommonResponse<Food>>

    @POST(HostUtil.DELETE_FOOD)
    fun delFood(@Body food: Food): Call<CommonResponse<Food>>

    @POST(HostUtil.UPDATE_FOOD)
    fun updateFood(@Body food: Food): Call<CommonResponse<Food>>

    @Multipart
    @POST(HostUtil.UPLOAD_IMG)
    fun uploadImg(@Part multipart: MultipartBody.Part): Call<CommonResponse<String>>
}