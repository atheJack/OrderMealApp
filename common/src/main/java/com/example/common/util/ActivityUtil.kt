package com.example.common.util

import android.app.Activity
import com.example.common.router.Router
import kotlin.collections.ArrayList

object ActivityUtil {
    private val list = ArrayList<Activity>()

    fun pushActivity(activity: Activity) {
        list.add(activity)
    }

    fun popActivity(activity: Activity) {
        list.remove(activity)
    }

    fun finishAll() {
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val activity = iterator.next()
            iterator.remove()
            activity.finish()
        }
    }

    fun backToMain () {
        while (list.isNotEmpty()) {
            val activity = list.removeLast()
            val arr = activity::class.java.toString().split(" ")
            LogUtil.d(arr.toString())
            if (arr[arr.size - 1] == Router.MAIN.pageName ||
                arr[arr.size - 1] == Router.MAIN_MANAGER.pageName){
                list.add(activity)
                break
            } else {
                activity.finish()
            }
        }
        LogUtil.d(list.toString())
    }
}