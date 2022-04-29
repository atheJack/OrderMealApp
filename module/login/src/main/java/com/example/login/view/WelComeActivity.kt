package com.example.login.view

import com.example.common.BaseActivity
import com.example.common.BaseViewModel
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.common.sharepreference.SharedPreferenceConst
import com.example.common.sharepreference.SharedPreferenceUtil
import com.example.common.util.LogUtil
import com.example.login.R
import java.lang.Exception

class WelComeActivity : BaseActivity<BaseViewModel>() {
    override fun createVm(): BaseViewModel {
        return BaseViewModel()
    }

    override fun onInit() {
        Thread {
            try {
                Thread.sleep(1000)
                jump()
            } catch (e: Exception) {
                e.printStackTrace()
                LogUtil.d("welcome activity start crash,caused by thread")
            }
        }.start()
    }

    private fun jump() {
        // 检查是否登录
        if (SharedPreferenceUtil.getInt(
                this, SharedPreferenceConst.LOGIN_STATE,
                SharedPreferenceConst.LoginStateValue.NO_LOGIN
            ) == SharedPreferenceConst.LoginStateValue.NO_LOGIN
        ) {
            // 检查是否注册
            if (SharedPreferenceUtil.getInt(
                    this, SharedPreferenceConst.REGISTER_STATE,
                    SharedPreferenceConst.RegisterStateValue.NO_REGISTER
                ) == SharedPreferenceConst.RegisterStateValue.NO_REGISTER
            ) {
                //未注册
                Navigation.jump(this, Router.REGISTER)
                finish()
            } else {
                // 注册了直接去登录
                Navigation.jump(this, Router.LOGIN)
                finish()
            }
        } else {
            // 跳转到主页
            val loginMode = SharedPreferenceUtil.getInt(this,
                SharedPreferenceConst.LOGIN_MODE, SharedPreferenceConst.LoginModeValue.USER_LOGIN)
            if (loginMode == SharedPreferenceConst.LoginModeValue.USER_LOGIN) {
                Navigation.jump(this, Router.MAIN)
            } else {
                Navigation.jump(this, Router.MAIN_MANAGER)
            }
            finish()
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_welcome
    }
}