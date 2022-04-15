package com.example.common.util

import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowManager

/**
 * 状态栏相关工具类
 *
 */
object StatusBarUtil {
    //设置Activity对应的顶部状态栏的颜色
    fun setWindowStatusBarColor(activity: Activity, colorResId: Int) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window: Window = activity.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.setStatusBarColor(activity.resources.getColor(colorResId))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
