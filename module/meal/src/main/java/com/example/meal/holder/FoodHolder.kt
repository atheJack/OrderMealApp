package com.example.meal.holder

import android.view.View
import android.widget.TextView
import com.example.common.recyclelist.CommonHolder
import com.example.common.recyclelist.CommonItemClickListener
import com.example.meal.R
import com.example.meal.model.Food

class FoodHolder(itemView: View) : CommonHolder<Food>(itemView){

    private val foodName = findViewById<TextView>(R.id.tv_food_name)
    private val foodNum = findViewById<TextView>(R.id.tv_food_num)
    private val foodPrice = findViewById<TextView>(R.id.tv_food_price)

    override fun bind(
        data: List<Food>,
        holder: CommonHolder<Food>,
        position: Int,
        listener: CommonItemClickListener<Food>?
    ) {
        foodName.text = data[position].name
        foodNum.text = "${data[position].num}"
        foodPrice.text = "￥${data[position].price}"
        listener?.onClick(itemView, data, position)
    }




}