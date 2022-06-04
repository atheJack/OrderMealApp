package com.example.common.recyclelist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.common.R

class TypeAdapter(var context: Context, var data: List<Int>, var selectPosition: Int,
                  var listener: CommonItemClickListener<Int>?): CommonAdapter<TypeHolder, Int>()  {
    constructor(context: Context,data: List<Int>, selectPosition: Int):this(context, data, selectPosition, null)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_food_type, parent, false)
        return TypeHolder(view)
    }

    override fun onBindViewHolder(holder: TypeHolder, position: Int) {
        holder.setSelectPosition(selectPosition)
        holder.bind(data, holder, position, listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}