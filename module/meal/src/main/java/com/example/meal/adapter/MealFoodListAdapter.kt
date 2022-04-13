package com.example.meal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.common.recyclelist.CommonAdapter
import com.example.meal.R
import com.example.meal.holder.FoodHolder

class MealFoodListAdapter(var context: Context, var data: List<String>): CommonAdapter<FoodHolder,String>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false)
        return FoodHolder(view)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bind(data, holder, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}