package com.example.common.network

object HostUtil {
    private const val PROTOCOL = "http://"
    private const val HOST = "xilelqs.top"
    private const val HOST_TEST = "192.168.31.13"
    private const val PORT = ":7322"
    const val URL = PROTOCOL + HOST + PORT
    const val URL_TEST = PROTOCOL + HOST_TEST + PORT

    /**
     * 注册
     */
    const val REGISTER = "/user/register"

    /**
     * 登录
     */
    const val LOGIN = "/user/login"

    /**
     * 菜品列表
     */
    const val GET_FOOD = "/food/get_all_food"

    /**
     * 更新菜品
     */
    const val UPDATE_FOOD = "/food/update"

    /**
     * 删除菜品
     */
    const val DELETE_FOOD = "/food/delete"

    /**
     * 添加菜品
     */
    const val ADD_FOOD = "/food/add"

    /**
     * 上传图片
     */
    const val UPLOAD_IMG = "/food/upload_img"

    /**
     * 获取所有订单
     */
    const val GET_ORDER = "/order/get_all_order"

    /**
     * 生成订单
     */
    const val GENERATE_ORDER = "/order/generate"

    /**
     * 获取指定user的订单
     */
    const val GET_USER_ORDER = "/order/get_user_order"

    /**
     * 更新订单状态
     */
    const val UPDATE_ORDER_STATE = "/order/update_state"

}