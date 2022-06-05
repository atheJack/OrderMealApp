package com.example.manager.view

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
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
import com.example.common.util.DialogUtil
import com.example.common.util.ToastUtil
import com.example.manager.R
import com.example.manager.viewmodel.ManagerViewModel
import com.example.manager.recyclelist.FoodListAdapter
import kotlinx.android.synthetic.main.activity_manager.*

class ManagerActivity : BaseActivity<ManagerViewModel>() {

    private var adapter: FoodListAdapter? = null
    private var typeAdapter: TypeAdapter? = null
    private var tempFood: Food = Food()
    private var tempType: Int = 0
    private lateinit var progressDialog: ProgressDialog

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
            val position = list.indexOf(tempType)
            typeAdapter!!.data = list
            typeAdapter!!.selectPosition = position
            typeAdapter!!.notifyDataSetChanged()
            showTypeFoodData(typeAdapter!!.data, position)
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
                if(progressDialog.isShowing) {
                    progressDialog.dismiss()
                }
            } else {
                ToastUtil.showToastShort(this, it.message)
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
                                    showDelDialog(data, position)
                                }
                            }
                        })
                    rv_meal_list.adapter = adapter
                    rv_meal_list.layoutManager = LinearLayoutManager(this)
                } else {
                    showTypeFoodData(typeAdapter!!.data, typeAdapter!!.selectPosition)
                }
            } else {
                ToastUtil.showToastLong(this, it.message)
            }
        })
    }

    private fun initView() {
        initGlide()
        progressDialog = DialogUtil.getLoadingDialog(this, "请等待", "正在更新信息..", false)
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

    private fun showDelDialog(data: List<Food>, position: Int) {
        AlertDialog.Builder(this)
            .setTitle("删除菜品")
            .setMessage("是否确认删除该菜品？")
            .setPositiveButton("删除") { dialog, which ->
                viewModel.delFood(data[position])
                val listArr = viewModel.foodListData.value!!.data as ArrayList<Food>
                listArr.remove(data[position])
                showTypeFoodData(typeAdapter!!.data, typeAdapter!!.selectPosition)
            }
            .setNegativeButton("取消"
            ) { dialog, which -> dialog.dismiss() }
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == foodEditCode && resultCode == RESULT_OK) {
            val foodBundle = data?.getBundleExtra("food_edit_finish")
            val food = foodBundle?.getSerializable("food") as Food?
            food?.let {
                progressDialog.show()
                tempType = food.type
                tempFood.name = food.name
                tempFood.price = food.price
                tempFood.imgUrl = food.imgUrl
                tempFood.type = food.type
                Handler(Looper.getMainLooper()).postDelayed({
                    viewModel.getFoodTypeList()
                }, 1000)
            }
        } else if (requestCode == foodAddCode && resultCode == RESULT_OK) {
            val foodBundle = data?.getBundleExtra("food_add_finish")
            val food = foodBundle?.getSerializable("food") as Food?
            val foodList = viewModel.foodListData.value?.data as ArrayList
            food?.let {
                progressDialog.show()
                tempType = food.type
                foodList.add(food)
                Handler(Looper.getMainLooper()).postDelayed({
                    viewModel.getFoodTypeList()
                }, 1000)
            }
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_manager
    }

}