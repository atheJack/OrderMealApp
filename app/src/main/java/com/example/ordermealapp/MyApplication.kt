package com.example.ordermealapp

import android.app.Application
import androidx.multidex.MultiDex
import com.example.common.ModuleServiceLoader
import com.example.meal.moduleapi.MealServiceImp
import com.example.meal_api.IMealService
import com.example.ordermealapp.task.ModuleServiceTask
import com.example.ordermealapp.task.XGPushTask


class MyApplication: Application() {

    companion object{
        private lateinit var instance: MyApplication

        fun getInstance(): MyApplication{
            return instance
        }
    }

    override fun onCreate() {
        MultiDex.install(this)
        ModuleServiceTask.excute(this)
        XGPushTask.excute(this)
        instance = this
        super.onCreate()
    }

}