package com.example.ordermealapp.task

import android.content.Context
import com.example.app_api.IAppService
import com.example.common.ModuleServiceLoader
import com.example.meal.moduleapi.MealServiceImp
import com.example.meal_api.IMealService
import com.example.ordermealapp.AppServiceImp

object ModuleServiceTask: ITask{
    override fun excute(context: Context) {
        ModuleServiceLoader.instance.apply {
            register(
                IMealService::class.java, MealServiceImp
            )
            register(
                IAppService::class.java, AppServiceImp
            )
        }
    }
}