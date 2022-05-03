package com.example.ordermealapp

import android.app.Application
import android.util.Log
import androidx.multidex.MultiDex
import com.example.common.ModuleServiceLoader
import com.example.common.util.LogUtil
import com.example.meal.moduleapi.MealServiceImp
import com.example.meal_api.IMealService
import com.tencent.android.tpush.*


class MyApplication: Application() {
    override fun onCreate() {
        initModuleService()
        MultiDex.install(this)
        XGPushConfig.enableDebug(this,true)
        XGPushManager.registerPush(this, object : XGIOperateCallback {
            override fun onSuccess(data: Any, flag: Int) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：$data")
            }

            override fun onFail(data: Any, errCode: Int, msg: String) {
                Log.d("TPush", "注册失败，错误码：$errCode,错误信息：$msg")
            }
        })
        XGPushManager.setNotifactionCallback(object :XGPushNotifactionCallback{
            override fun handleNotify(p0: XGNotifaction?) {
                LogUtil.d("XGpush" + p0?.content.toString())
            }

        })
        super.onCreate()
    }

    private fun initModuleService() {
        ModuleServiceLoader.instance.register(
            IMealService::class.java, MealServiceImp
        )
    }
}