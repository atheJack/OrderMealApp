package com.example.common.router
/**
 * @author jack
 * 目前问题，用起来比较麻烦，需要清楚知道activity的包名以及名称
 */
object Router{
    val LOGIN = PageInfo("com.example.login.LoginActivity")
    val USER_CHOOSE = PageInfo("com.example.login.UserChooseActivity")
    val MEAL = PageInfo("com.example.meal.MealActivity")
}