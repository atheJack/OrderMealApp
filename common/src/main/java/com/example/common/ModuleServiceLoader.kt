package com.example.common

class ModuleServiceLoader private constructor() {
    companion object {
        // 单例
        val instance by lazy { ModuleServiceLoader() }
    }

    // 维护[external模块中对外暴露的接口名]与[内部模块接口实现类]的绑定
    private val serviceMap: HashMap<String, Any> = HashMap()

    // 模块初始化时调用
    fun <T : Any> register(apiClass: Class<T>, apiImpl: T) {
        serviceMap[apiClass.name] = apiImpl
    }

    // 模块调用get拿到其他模块的apiImpl，达到通信的目的
    fun <T : Any> get(apiClass: Class<T>): T? {
        return serviceMap[apiClass.name] as? T
    }
}