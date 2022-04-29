package com.example.manager.recyclelist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.common.model.Food
import com.example.common.recyclelist.CommonHolder
import com.example.common.recyclelist.CommonItemClickListener
import com.example.manager.R

class FoodManagerHolder(itemView: View) : CommonHolder<Food>(itemView) {
    private val foodName = findViewById<TextView>(R.id.tv_food_name)
    private val foodPrice = findViewById<TextView>(R.id.tv_food_price)
    private val foodImg = findViewById<ImageView>(R.id.iv_food_img)

    override fun bind(
        data: List<Food>,
        holder: CommonHolder<Food>,
        position: Int,
        listener: CommonItemClickListener<Food>?
    ) {
        foodName.text = data[position].name
        foodPrice.text = "￥${data[position].price}"
        val url = data[position].imgUrl
        Glide.with(itemView).load(url).into(foodImg)
        listener?.onClick(itemView, data, position)
    }
}