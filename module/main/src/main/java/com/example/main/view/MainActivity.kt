package com.example.main.view

import com.example.common.BaseActivity
import com.example.common.BaseViewModel
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.common.sharepreference.SharedPreferenceConst
import com.example.common.sharepreference.SharedPreferenceUtil
import com.example.common.util.ActivityUtil
import com.example.main.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity<BaseViewModel>() {
    override fun createVm(): BaseViewModel {
        return BaseViewModel()
    }

    override fun onInit() {
        initView()
    }

    private fun initView() {
        ll_start_order.setOnClickListener {
            Navigation.jump(this, Router.MEAL)
        }
        ll_my_order.setOnClickListener {
            Navigation.jump(this, Router.MY_ORDER)
        }
        ll_login.setOnClickListener {
            clearSp()
            ActivityUtil.finishAll()
            Navigation.jump(this, Router.LOGIN)
        }
        ll_exit.setOnClickListener {
            ActivityUtil.finishAll()
        }
    }

    private fun clearSp() {
        SharedPreferenceUtil.putIntAsync(this, SharedPreferenceConst.LOGIN_STATE, SharedPreferenceConst.LoginStateValue.NO_LOGIN)
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_main
    }
}