package com.example.meal.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.common.recyclelist.CommonHolder
import com.example.common.recyclelist.CommonItemClickListener
import com.example.meal.R
import com.example.common.model.Food
import com.example.common.util.GlideUtil

class FoodHolder(itemView: View) : CommonHolder<Food>(itemView){

    private val foodName = findViewById<TextView>(R.id.tv_food_name)
    private val foodNum = findViewById<TextView>(R.id.tv_food_num)
    private val foodPrice = findViewById<TextView>(R.id.tv_food_price)
    private val foodImg = findViewById<ImageView>(R.id.iv_food_img)

    override fun bind(
        data: List<Food>,
        holder: CommonHolder<Food>,
        position: Int,
        listener: CommonItemClickListener<Food>?
    ) {
        foodName.text = data[position].name
        foodNum.text = "${data[position].num}"
        foodPrice.text = "￥${data[position].price}"
        val url = data[position].imgUrl
        GlideUtil.loadUrl(itemView.context, url, foodImg)
        listener?.onClick(itemView, data, position)
    }




}