package com.example.common.util

import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowManager

object StatusBarUtil {
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
