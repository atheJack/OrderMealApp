package com.example.common.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(HostUtil.URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> getApi(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}