package com.example.common.sharepreference

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceUtil {
    fun getSp(context: Context): SharedPreferences{
        return context.getSharedPreferences("my_default", Context.MODE_PRIVATE)
    }

    fun contains(context: Context, key: String): Boolean {
        return getSp(context).contains(key)
    }

    fun putIntSync(context: Context, key: String, value: Int) {
        getSp(context).edit().apply{
            putInt(key, value)
            commit()
        }
    }

    fun putIntAsync(context: Context, key: String, value: Int) {
        getSp(context).edit().apply{
            putInt(key, value)
            apply()
        }
    }

    fun putLongSync(context: Context, key: String, value: Long) {
        getSp(context).edit().apply{
            putLong(key, value)
            commit()
        }
    }

    fun getInt(context: Context, key: String, defaultValue: Int): Int {
        return getSp(context).getInt(key, defaultValue)
    }

    fun getLong(context: Context, key: String, defaultValue: Long): Long {
        return getSp(context).getLong(key, defaultValue)
    }

}