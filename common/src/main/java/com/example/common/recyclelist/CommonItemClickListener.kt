package com.example.common.recyclelist

import android.view.View

interface CommonItemClickListener<T> {
    fun onClick(itemView: View, data: List<T>, position: Int)
}