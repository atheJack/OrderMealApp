package com.example.common.router
object Router{

    val LOGIN = PageInfo("com.example.login.view.LoginActivity")

    val REGISTER = PageInfo("com.example.login.view.RegisterActivity")

    val MEAL = PageInfo("com.example.meal.MealActivity")

    val ORDER_DETAIL = PageInfo("com.example.order.view.OrderDetailActivity")
    val MY_ORDER = PageInfo("com.example.order.view.MyOrderActivity")
    val ORDER_FINISH =PageInfo("com.example.order.view.OrderFinishActivity")
    val ORDER_MANAGER = PageInfo("com.example.order.view.ManagerOrderActivity")

    val MANAGER = PageInfo("com.example.manager.view.ManagerActivity")
    val FOOD_EDIT = PageInfo("com.example.manager.view.FoodEditActivity")
    val ADD_FOOD = PageInfo("com.example.manager.view.AddFoodActivity")

    val MAIN = PageInfo("com.example.main.view.MainActivity")
    val MAIN_MANAGER = PageInfo("com.example.main.view.MainActivityM")
}