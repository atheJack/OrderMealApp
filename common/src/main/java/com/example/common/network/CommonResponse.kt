package com.example.common.network

data class CommonResponse<T>(var code:Int, var message:String, var data:T)