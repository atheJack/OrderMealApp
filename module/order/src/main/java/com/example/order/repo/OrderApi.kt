package com.example.order.repo

import com.example.common.model.Order
import com.example.common.model.User
import com.example.common.network.CommonResponse
import com.example.common.network.HostUtil
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OrderApi {
    @POST(HostUtil.GENERATE_ORDER)
    fun generateOrder(@Body order: Order): Call<CommonResponse<Order>>

    @GET(HostUtil.GET_ORDER)
    fun getAllOrder(): Call<CommonResponse<List<Order>>>

    @POST(HostUtil.GET_USER_ORDER)
    fun getUserOrder(@Body user: User): Call<CommonResponse<List<Order>>>

    @POST(HostUtil.UPDATE_ORDER_STATE)
    fun updateOrderState(@Body order: Order): Call<CommonResponse<Order>>
}