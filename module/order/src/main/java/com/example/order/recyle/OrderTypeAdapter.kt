package com.example.order.recyle

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.common.R
import com.example.common.recyclelist.CommonAdapter
import com.example.common.recyclelist.CommonItemClickListener

class OrderTypeAdapter(var context: Context, var data: List<Int>, var selectPosition: Int,
                       var listener: CommonItemClickListener<Int>?): CommonAdapter<OrderTypeHolder, Int>()  {
    constructor(context: Context,data: List<Int>, selectPosition: Int):this(context, data, selectPosition, null)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderTypeHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_food_type, parent, false)
        return OrderTypeHolder(view)
    }

    override fun onBindViewHolder(holderOrder: OrderTypeHolder, position: Int) {
        holderOrder.setSelectPosition(selectPosition)
        holderOrder.bind(data, holderOrder, position, listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}