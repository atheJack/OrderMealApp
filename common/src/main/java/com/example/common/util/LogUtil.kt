package com.example.common.util

import android.util.Log

object LogUtil {
    private const val TAG = "lqs"

    fun d(msg: String) {
        Log.d(TAG, msg)
    }

    fun dWithTag(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    fun w(msg: String) {
        Log.w(TAG, msg)
    }
}