package com.example.meal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.common.recyclelist.CommonAdapter
import com.example.common.recyclelist.CommonItemClickListener
import com.example.meal.R
import com.example.meal.holder.SmallFoodHolder
import com.example.common.model.Food

class ShopCartFoodListAdapter(var context: Context, var data: List<Food>,
                              var listener: CommonItemClickListener<Food>?): CommonAdapter<SmallFoodHolder, Food>()  {

    constructor(context: Context, data: List<Food>): this(context, data, null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallFoodHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_small_food, parent, false)
        return SmallFoodHolder(view)
    }

    override fun onBindViewHolder(holder: SmallFoodHolder, position: Int) {
        holder.bind(data, holder, position, listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}