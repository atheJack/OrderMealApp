package com.example.common.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun timeToDate (time: Long): String {
        return timeToDate(time, DateFormat.MEDIUM)
    }

    fun timeToDate (time: Long, style: Int): String{
        val df = DateFormat.getDateInstance(style, Locale.CHINA)
        val dft = DateFormat.getTimeInstance(style, Locale.CHINA)
        val date = df.format(Date(time)) + " " + dft.format(Date(time))
        return date
    }

    fun timeToString(time: Long): String {
        val f = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
        return f.format(Date(time))
    }

}