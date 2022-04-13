package com.example.common.network

object HostUtil {
    private const val PROTROL = "http://"
    private const val HOST = "xilelqs.top"
    private const val HOST_TEST = "192.168.31.13"
    private const val PORT = ":7322"
    const val URL = PROTROL + HOST + PORT
    const val URL_TEST = PROTROL + HOST_TEST + PORT
    const val STUDENT = "/student"
    const val TEST = "/test"

    /**
     * 注册
     */
    const val REGISTER = "/user/register"

    /**
     * 注册2，表单形式
     */
    const val REGISTER2 = "/user/register2"

    /**
     * 登录
     */
    const val LOGIN = "/user/login"

}