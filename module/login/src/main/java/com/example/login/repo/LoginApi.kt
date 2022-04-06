package com.example.login.repo

import com.example.common.network.HostUtil
import com.example.common.network.CommonResponse
import com.example.login.model.Test
import com.example.login.model.User
import retrofit2.Call
import retrofit2.http.*

interface LoginApi {
    //todo 还没写
    @POST
    fun login(): Call<User>

    @GET(HostUtil.TEST)
    fun getTest(): Call<CommonResponse<List<Test>>>

    @POST("/user/register")
    fun register(@Body user: User): Call<CommonResponse<User>>

    @POST("/user/register2")
    @FormUrlEncoded
    fun register2(@Field(value = "name")name: String, @Field(value = "password")password: String,
                  @Field(value = "is_manager")is_manager: Boolean): Call<CommonResponse<User>>
}