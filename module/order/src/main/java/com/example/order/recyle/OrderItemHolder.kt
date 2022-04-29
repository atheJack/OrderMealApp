package com.example.order.recyle

import android.view.View
import android.widget.TextView
import com.example.common.model.Order
import com.example.common.recyclelist.CommonHolder
import com.example.common.recyclelist.CommonItemClickListener
import com.example.common.util.DateUtil
import com.example.order.R

class OrderItemHolder(itemView: View) : CommonHolder<Order>(itemView) {
    private val orderNumText = itemView.findViewById<TextView>(R.id.tv_order_num)
    private val orderStateText = itemView.findViewById<TextView>(R.id.tv_order_state)
    private val orderMealText = itemView.findViewById<TextView>(R.id.tv_order_meal)
    private val orderTotalNUm = itemView.findViewById<TextView>(R.id.tv_order_food_total_num)
    private val orderTotalPrice = itemView.findViewById<TextView>(R.id.tv_order_total_price)
    private val orderDateText = itemView.findViewById<TextView>(R.id.tv_order_date)
    override fun bind(
        data: List<Order>,
        holder: CommonHolder<Order>,
        position: Int,
        listener: CommonItemClickListener<Order>?
    ) {
        val order = data[position]
        orderNumText.text = "订单号：${String.format("%03d", order.id)}"
        if (order.state == 1) {
            orderStateText.text = "待处理"
        } else if(order.state == 2){
            orderStateText.text = "处理中"
        } else {
            orderStateText.text = "已完成"
        }
        orderDateText.text = DateUtil.timeToString(order.date)
        orderTotalNUm.text = "共${order.totalNum}件"
        orderTotalPrice.text = "共计：￥${order.totalPrice}"
        orderMealText.text = ""
        val foodList = order.foodList
        foodList.indices.forEach { i ->
            if( i != foodList.size - 1) {
                orderMealText.append("${foodList[i].name}x${foodList[i].num},")
            } else {
                orderMealText.append("${foodList[i].name}x${foodList[i].num}")
            }
        }
        listener?.onClick(itemView, data, position)
    }
}