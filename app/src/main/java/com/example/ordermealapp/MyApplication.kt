package com.example.ordermealapp
import android.app.Application
import com.example.common.ModuleServiceLoader
import com.example.meal.moduleapi.MealServiceImp
import com.example.meal_api.MealService

class MyApplication: Application() {
    override fun onCreate() {
        initModuleService()
        super.onCreate()
    }

    private fun initModuleService() {
        ModuleServiceLoader.instance.register(
            MealService::class.java, MealServiceImp
        )
    }
}