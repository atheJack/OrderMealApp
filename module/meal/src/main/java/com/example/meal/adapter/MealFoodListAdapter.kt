package com.example.meal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.common.recyclelist.CommonAdapter
import com.example.common.recyclelist.CommonItemClickListener
import com.example.meal.R
import com.example.meal.holder.FoodHolder
import com.example.meal.model.Food

class MealFoodListAdapter(var context: Context, var data: List<Food>,
                          var listener: CommonItemClickListener<Food>?): CommonAdapter<FoodHolder,Food>() {

    constructor(context: Context, data: List<Food>): this(context, data, null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false)
        return FoodHolder(view)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bind(data, holder, position, listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}