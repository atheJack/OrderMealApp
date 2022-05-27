package com.example.common

class ModuleServiceLoader private constructor() {
    companion object {
        val instance by lazy { ModuleServiceLoader() }
    }

    private val serviceMap: HashMap<String, Any> = HashMap()

    fun <T : Any> register(apiClass: Class<T>, apiImpl: T) {
        serviceMap[apiClass.name] = apiImpl
    }

    fun <T : Any> get(apiClass: Class<T>): T? {
        return serviceMap[apiClass.name] as? T
    }
}