package com.example.common.recyclelist

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.example.common.R


class TypeHolder(itemView: View) : CommonHolder<Int>(itemView) {
    private val foodTypeName = findViewById<TextView>(R.id.tv_type_name)
    private val foodSelectLine = findViewById<View>(R.id.v_select_line)
    private var selectPosition = 0
    override fun bind(
        data: List<Int>,
        holder: CommonHolder<Int>,
        position: Int,
        listener: CommonItemClickListener<Int>?
    ) {
        var text = "全部菜品"
        when(data[position]){
            0 -> {
                text = "荤菜"
            }
            1 -> {
                text = "素菜"
            }
            2 -> {
                text = "粉面"
            }
            3 -> {
                text = "主食"
            }
            4 -> {
                text = "汤"
            }
            5 -> {
                text = "饮料"
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