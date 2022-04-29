package com.example.ordermealapp
import android.app.Application
import androidx.multidex.MultiDex
import com.example.common.ModuleServiceLoader
import com.example.meal.moduleapi.MealServiceImp
import com.example.meal_api.IMealService

class MyApplication: Application() {
    override fun onCreate() {
        initModuleService()
        MultiDex.install(this)
        super.onCreate()
    }

    private fun initModuleService() {
        ModuleServiceLoader.instance.register(
            IMealService::class.java, MealServiceImp
        )
    }
}