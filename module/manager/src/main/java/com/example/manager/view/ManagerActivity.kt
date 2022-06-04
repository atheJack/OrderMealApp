package com.example.manager.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.BaseActivity
import com.example.common.model.Food
import com.example.common.recyclelist.CommonItemClickListener
import com.example.common.recyclelist.TypeAdapter
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.common.sharepreference.SharedPreferenceConst
import com.example.common.sharepreference.SharedPreferenceUtil
import com.example.common.util.ToastUtil
import com.example.manager.R
import com.example.manager.viewmodel.ManagerViewModel
import com.example.manager.recyclelist.FoodListAdapter
import kotlinx.android.synthetic.main.activity_manager.*

class ManagerActivity : BaseActivity<ManagerViewModel>() {

    private var adapter: FoodListAdapter? = null
    private var typeAdapter: TypeAdapter? = null
    private var tempFood: Food = Food()

    companion object {
        const val foodEditCode = 1
        const val foodAddCode = 2
    }

    override fun createVm(): ManagerViewModel {
        return ManagerViewModel()
    }

    override fun onInit() {
        initView()
        initObserver()
    }

    private fun initTypeAdapter(list: List<Int>) {
        if(typeAdapter == null) {
            typeAdapter = TypeAdapter(this, list, 0)
            typeAdapter!!.listener = object:CommonItemClickListener<Int>{
                override fun onClick(itemView: View, data: List<Int>, position: Int) {
                    itemView.setOnClickListener {
                        typeAdapter!!.selectPosition = position
                        typeAdapter!!.notifyDataSetChanged()
                        showTypeFoodData(data, position)
                    }
                }
            }
            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            rv_type_list.layoutManager = layoutManager
            rv_type_list.adapter = typeAdapter
        } else {
            typeAdapter!!.notifyDataSetChanged()
        }
    }

    private fun getList(list: List<Int>) : List<Int>{
        val arrayList = ArrayList<Int>()
        arrayList.add(-1)
        arrayList.addAll(list)
        return arrayList
    }

    private fun showTypeFoodData(data: List<Int>, position: Int) {
        val foodList = viewModel.foodListData.value?.data
        if(!foodList.isNullOrEmpty()) {
            val type = data[position]
            val newFoodList = ArrayList<Food>()
            when(type){
                -1 -> {
                    newFoodList.addAll(foodList)
                }
                else -> {
                    for(food in foodList) {
                        if(food.type == type) {
                            newFoodList.add(food)
                        }
                    }
                }
            }
            adapter?.let {
                adapter!!.data = newFoodList
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    private fun initObserver() {
        viewModel.foodTypeData.observe(this, {
            if(it.code == 200) {
                viewModel.getFoodList()
                initTypeAdapter(getList(it.data))
            } else {

            }
        })
        viewModel.foodListData.observe(this, {
            if (it.code == 200) {
                if (adapter == null) {
                    adapter = FoodListAdapter(
                        this,
                        it.data as ArrayList<Food>,
                        object : CommonItemClickListener<Food> {
                            override fun onClick(itemView: View, data: List<Food>, position: Int) {
                                val editFoodBt = itemView.findViewById<ImageView>(R.id.iv_food_edit)
                                val delFoodBt = itemView.findViewById<ImageView>(R.id.iv_food_del)
                                editFoodBt.setOnClickListener {
                                    Navigation.jumpWithBundleForResult(
                                        this@ManagerActivity,
                                        Router.FOOD_EDIT,
                                        foodEditCode,
                                        Bundle().apply {
                                            putSerializable("food", data[position])
                                            tempFood = data[position]
                                        })
                                }
                                delFoodBt.setOnClickListener {
                                    viewModel.delFood(data[position])
                                    val listArr: ArrayList<Food> = data as ArrayList<Food>
                                    listArr.remove(data[position])
                                    adapter!!.notifyDataSetChanged()
                                }
                            }
                        })
                    rv_meal_list.adapter = adapter
                    rv_meal_list.layoutManager = LinearLayoutManager(this)
                } else {
                    adapter!!.data = it.data as ArrayList<Food>
                    adapter!!.notifyDataSetChanged()
                }
            } else {
                ToastUtil.showToastLong(this, it.message)
            }
        })
    }

    private fun initView() {
        initGlide()
        viewModel.getFoodTypeList()
        fb_add_food.setOnClickListener {
            Navigation.jumpForResult(this, Router.ADD_FOOD, foodAddCode)
        }
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initGlide() {
        if(!SharedPreferenceUtil.contains(this, SharedPreferenceConst.GLIDE_SIGN)){
            SharedPreferenceUtil.putLongSync(this, SharedPreferenceConst.GLIDE_SIGN, System.currentTimeMillis())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == foodEditCode && resultCode == RESULT_OK) {
            val foodBundle = data?.getBundleExtra("food_edit_finish")
            val food = foodBundle?.getSerializable("food") as Food?
            food?.let {
                tempFood.name = food.name
                tempFood.price = food.price
                tempFood.imgUrl = food.imgUrl
                adapter?.notifyDataSetChanged()
            }
            Handler(Looper.getMainLooper()).postDelayed({
                viewModel.getFoodList()
            }, 2000)
        } else if (requestCode == foodAddCode && resultCode == RESULT_OK) {
            val foodBundle = data?.getBundleExtra("food_add_finish")
            val food = foodBundle?.getSerializable("food") as Food?
            food?.let {
                adapter?.let {
                    it.data.add(food)
                    it.notifyDataSetChanged()
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({
                viewModel.getFoodList()
            }, 2000)
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_manager
    }

}