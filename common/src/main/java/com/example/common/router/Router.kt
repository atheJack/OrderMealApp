package com.example.common.router
/**
 * @author jack
 * 目前问题，用起来比较麻烦，需要清楚知道activity的包名以及名称
 */

//val USER_CHOOSE = PageInfo("com.example.login.view.UserChooseActivity")
object Router{

    //用户登录页面
    val LOGIN = PageInfo("com.example.login.view.LoginActivity")
    //用户注册页面
    val REGISTER = PageInfo("com.example.login.view.RegisterActivity")
    //用户点餐页面
    val MEAL = PageInfo("com.example.meal.MealActivity")
    //订单详情页面
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