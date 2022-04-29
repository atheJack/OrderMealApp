package com.example.main.view

import com.example.common.BaseActivity
import com.example.common.BaseViewModel
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.common.util.ActivityUtil
import com.example.main.R
import kotlinx.android.synthetic.main.activity_main_m.*

class MainActivityM: BaseActivity<BaseViewModel>() {
    override fun createVm(): BaseViewModel {
        return BaseViewModel()
    }

    override fun onInit() {
        ll_manager_meal.setOnClickListener {
            Navigation.jump(this, Router.MANAGER)
        }
        ll_manager_order.setOnClickListener {
            Navigation.jump(this, Router.ORDER_MANAGER)
        }
        ll_login.setOnClickListener {
            Navigation.jump(this, Router.LOGIN)
        }
        ll_exit.setOnClickListener {
            ActivityUtil.finishAll()
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_main_m
    }
}