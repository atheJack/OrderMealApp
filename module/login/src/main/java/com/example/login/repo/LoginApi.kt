package com.example.login.repo

import com.example.common.network.HostUtil
import com.example.common.network.CommonResponse
import com.example.common.model.User
import retrofit2.Call
import retrofit2.http.*

interface LoginApi {

    @POST(HostUtil.LOGIN)
    fun login(@Body user: User): Call<CommonResponse<User>>

    @POST(HostUtil.REGISTER)
    fun register(@Body user: User): Call<CommonResponse<User>>
}