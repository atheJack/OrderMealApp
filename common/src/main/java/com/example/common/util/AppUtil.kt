package com.example.common.util

import android.app.Application
import com.example.app_api.IAppService
import com.example.common.ModuleServiceLoader

object AppUtil {
    val api = ModuleServiceLoader.instance.get(IAppService::class.java)

    fun getApplicationContext(): Application{
        return api!!.getApplicationContext()
    }

    fun addXGPushNotifyCallback(block: () -> Unit){
        api?.addXGPushNotifyCallback {
            block.invoke()
        }
    }
}