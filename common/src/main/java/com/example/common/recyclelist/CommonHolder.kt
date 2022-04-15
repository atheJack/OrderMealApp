package com.example.common.recyclelist

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class CommonHolder<E>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun <T : View?> findViewById(id: Int): T {
        return itemView.findViewById<T>(id)
    }

    abstract fun bind(data: List<E>, holder: CommonHolder<E>, position: Int, listener: CommonItemClickListener<E>?)
}