package com.example.order.recyle

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.example.common.R
import com.example.common.recyclelist.CommonHolder
import com.example.common.recyclelist.CommonItemClickListener


class OrderTypeHolder(itemView: View) : CommonHolder<Int>(itemView) {
    private val foodTypeName = findViewById<TextView>(R.id.tv_type_name)
    private val foodSelectLine = findViewById<View>(R.id.v_select_line)
    private var selectPosition = 0
    override fun bind(
        data: List<Int>,
        holder: CommonHolder<Int>,
        position: Int,
        listener: CommonItemClickListener<Int>?
    ) {
        var text = "待处理"
        when(data[position]){
            1 -> {
                text = "待处理"
            }
            2 -> {
                text = "处理中"
            }
            3 -> {
                text = "已完成"
            }
        }
        foodTypeName.text = text
        if(selectPosition == position) {
            foodTypeName.setTextColor(Color.BLACK)
            foodSelectLine.visibility = View.VISIBLE

        } else {
            foodTypeName.setTextColor(Color.GRAY)
            foodSelectLine.visibility = View.GONE
        }
        listener?.onClick(itemView, data, position)
    }

    fun setSelectPosition(position: Int) {
        selectPosition = position
    }
}