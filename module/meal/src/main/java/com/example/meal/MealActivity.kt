package com.example.meal

import android.util.Log
import com.example.common.BaseActivity
import com.example.common.router.Navigation

class MealActivity: BaseActivity() {
    override fun onInit() {
        testReceive()
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_meal
    }

    fun testReceive() {
        val intent = intent
        val bundle = intent.getBundleExtra(Navigation.EXTRA_DATA)
        bundle?.getString("ceshi")?.let { Log.d("lqs", it) }
    }
}