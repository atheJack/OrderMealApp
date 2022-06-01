package com.example.main.view

import android.os.Handler
import android.os.Looper
import androidx.room.util.DBUtil
import com.example.common.BaseActivity
import com.example.common.BaseViewModel
import com.example.common.database.DbManager
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
        Thread{
            val userList = DbManager.getInstance().getDb(this).userDao().getAll()
            if(!userList.isNullOrEmpty()) {
                val user = userList[0]
                val username = user.name
                Handler(Looper.getMainLooper()).post {
                    tv_username.text = "你好，${username}"
                }
            }
        }.start()
    }

    private fun clearSp() {
        SharedPreferenceUtil.putIntAsync(this, SharedPreferenceConst.LOGIN_STATE, SharedPreferenceConst.LoginStateValue.NO_LOGIN)
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_main
    }
}