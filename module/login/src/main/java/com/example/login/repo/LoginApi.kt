package com.example.login.repo

import com.example.common.network.HostUtil
import com.example.common.network.CommonResponse
import com.example.login.model.Test
import com.example.login.model.User
import retrofit2.Call
import retrofit2.http.*

interface LoginApi {
    @POST(HostUtil.LOGIN)
    fun login(@Body user: User): Call<CommonResponse<User>>

    @GET(HostUtil.TEST)
    fun getTest(): Call<CommonResponse<List<Test>>>

    @POST(HostUtil.REGISTER)
    fun register(@Body user: User): Call<CommonResponse<User>>

    @POST(HostUtil.REGISTER2)
    @FormUrlEncoded
    fun register2(@Field(value = "name")name: String, @Field(value = "password")password: String,
                  @Field(value = "is_manager")is_manager: Boolean): Call<CommonResponse<User>>
}