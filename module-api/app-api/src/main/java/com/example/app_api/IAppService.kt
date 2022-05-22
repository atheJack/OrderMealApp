package com.example.app_api

import android.app.Application

interface IAppService {

    fun getApplicationContext(): Application

    fun addXGPushNotifyCallback(block: () -> Unit)

}