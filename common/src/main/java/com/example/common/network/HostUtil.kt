package com.example.common.network

object HostUtil {
    private const val PROTOCOL = "http://"
    private const val HOST = "xilelqs.top"
    private const val HOST_TEST = "192.168.31.13"
    private const val PORT = ":7322"
    const val URL = PROTOCOL + HOST + PORT
    const val URL_TEST = PROTOCOL + HOST_TEST + PORT

    const val REGISTER = "/user/register"

    const val LOGIN = "/user/login"

    const val GET_FOOD = "/food/get_all_food"

    const val GET_FOOD_TYPE = "/food/get_food_type"

    const val UPDATE_FOOD = "/food/update"

    const val DELETE_FOOD = "/food/delete"

    const val ADD_FOOD = "/food/add"

    const val UPLOAD_IMG = "/food/upload_img"

    const val GET_ORDER = "/order/get_all_order"

    const val GENERATE_ORDER = "/order/generate"

    const val GET_USER_ORDER = "/order/get_user_order"

    const val UPDATE_ORDER_STATE = "/order/update_state"

}