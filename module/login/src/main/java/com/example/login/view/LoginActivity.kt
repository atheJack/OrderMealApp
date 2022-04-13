package com.example.login.view
import android.os.Bundle
import com.example.common.BaseActivity
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.common.util.ToastUtil
import com.example.login.R
import com.example.login.model.User
import com.example.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginViewModel>() {
    override fun createVm(): LoginViewModel {
        return LoginViewModel()
    }

    private var mode = -1

    companion object{
        const val userLogin = 1
        const val managerLogin = 2
        const val loginMode = "login_mode"
    }

    override fun onInit() {
        initView()
        initObserver()
    }

    private fun initView() {
        val intent = intent
        mode = intent.getIntExtra(loginMode, -1)
        bt_login.setOnClickListener {
            login()
        }
    }

    private fun initObserver() {
        viewModel.loginData.observe(this, {
            if(it.code == 200) {
                Navigation.jump(this, Router.MEAL)
            } else {
                ToastUtil.showToastShort(this, it.message)
            }
        })
    }

    private fun login() {
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        //todo -> upload username password mode
        if (mode == userLogin) {
            // jump to user
        } else if(mode == managerLogin){
            // jump to manager
        }
        viewModel.login(User(1, username, password, false))
    }

    fun testNav() {
        Navigation.jumpWithBundle(this, Router.MEAL, Bundle().apply {
            putString("ceshi", "lqs lqs lqs")
        })
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_login
    }

}