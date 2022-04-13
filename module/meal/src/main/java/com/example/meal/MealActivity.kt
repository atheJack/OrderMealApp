package com.example.meal

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.BaseActivity
import com.example.common.BaseViewModel
import com.example.common.router.Navigation
import com.example.meal.adapter.MealFoodListAdapter
import kotlinx.android.synthetic.main.activity_meal.*

class MealActivity: BaseActivity<BaseViewModel>() {
    override fun onInit() {
        //testReceive()
        initList()
    }

    private fun initList() {
        val list = ArrayList<String>().apply {
            add("鱼香肉丝")
            add("宫保鸡丁")
            add("鱼香肉丝")
            add("宫保鸡丁")
            add("鱼香肉丝")
            add("宫保鸡丁")
            add("鱼香肉丝")
            add("宫保鸡丁")
        }
        val adapter = MealFoodListAdapter(this,list)
        val layoutManager = LinearLayoutManager(this)
        rv_meal_list.layoutManager = layoutManager
        rv_meal_list.adapter = adapter
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_meal
    }

    private fun testReceive() {
        val intent = intent
        val bundle = intent.getBundleExtra(Navigation.EXTRA_DATA)
        bundle?.getString("ceshi")?.let { Log.d("lqs", it) }
    }

    override fun createVm(): BaseViewModel {
        return BaseViewModel()
    }
}