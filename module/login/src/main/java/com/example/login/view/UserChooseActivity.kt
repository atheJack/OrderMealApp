package com.example.login.view

import com.example.common.BaseActivity
import com.example.common.BaseViewModel
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.common.sharepreference.SharedPreferenceConst
import com.example.common.sharepreference.SharedPreferenceUtil
import com.example.login.R
import kotlinx.android.synthetic.main.activity_user_choose.*

/**
 * 目前不准备使用，只允许用户进行注册，管理员不需要注册
 */
class UserChooseActivity: BaseActivity<BaseViewModel>() {

    override fun onInit() {
        ll_user.setOnClickListener {
            if(!SharedPreferenceUtil.contains(this, SharedPreferenceConst.LOGIN_MODE)) {
                SharedPreferenceUtil.putIntAsync(this,
                    SharedPreferenceConst.LOGIN_MODE, SharedPreferenceConst.LoginModeValue.USER_LOGIN)
            }
            Navigation.jump(this, Router.REGISTER)
            finish()
        }
        ll_manager.setOnClickListener {
            if(!SharedPreferenceUtil.contains(this, SharedPreferenceConst.LOGIN_MODE)) {
                SharedPreferenceUtil.putIntAsync(this,
                    SharedPreferenceConst.LOGIN_MODE, SharedPreferenceConst.LoginModeValue.MANAGER_LOGIN)
            }
            Navigation.jump(this, Router.REGISTER)
            finish()
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_user_choose
    }

    override fun createVm(): BaseViewModel {
        return BaseViewModel()
    }

}