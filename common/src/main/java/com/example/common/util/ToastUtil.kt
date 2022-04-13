package com.example.common.util

import android.content.Context
import android.widget.Toast

object ToastUtil {
    fun showToastLong(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    fun showToastShort(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}