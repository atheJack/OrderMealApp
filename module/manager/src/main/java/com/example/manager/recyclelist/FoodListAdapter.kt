package com.example.manager.recyclelist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.common.model.Food
import com.example.common.recyclelist.CommonAdapter
import com.example.common.recyclelist.CommonItemClickListener
import com.example.manager.R

class FoodListAdapter(var context: Context, var data: ArrayList<Food>, var listener: CommonItemClickListener<Food>?): CommonAdapter<FoodManagerHolder, Food>() {

    constructor(context: Context, data: ArrayList<Food>): this(context, data, null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodManagerHolder {
        return FoodManagerHolder(LayoutInflater.from(context).inflate(R.layout.item_food_list, parent, false))
    }

    override fun onBindViewHolder(holder: FoodManagerHolder, position: Int) {
        holder.bind(data, holder, position, listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}