package com.example.order.recyle

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.common.model.Order
import com.example.common.recyclelist.CommonAdapter
import com.example.common.recyclelist.CommonItemClickListener
import com.example.order.R

class MyOrderListAdapter(var context: Context, var data: List<Order>, var listener: CommonItemClickListener<Order>): CommonAdapter<OrderItemHolder, Order>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false)
        return OrderItemHolder(view)
    }

    override fun onBindViewHolder(holder: OrderItemHolder, position: Int) {
        holder.bind(data, holder, position, listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}