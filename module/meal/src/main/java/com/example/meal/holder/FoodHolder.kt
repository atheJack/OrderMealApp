package com.example.meal.holder

import android.view.View
import android.widget.TextView
import com.example.common.recyclelist.CommonHolder
import com.example.meal.R

class FoodHolder(itemView: View) : CommonHolder<String>(itemView){
    private val foodName= findViewById<TextView>(R.id.tv_food_name)

    override fun bind(data: List<String>, holder: CommonHolder<String>, position: Int) {
        foodName.text = data.get(position)
    }
}