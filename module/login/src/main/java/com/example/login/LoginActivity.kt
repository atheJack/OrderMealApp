package com.example.login

import android.os.Bundle
import com.example.common.BaseActivity
import com.example.common.router.Navigation
import com.example.common.router.Router
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private var mode = -1

    companion object{
        const val userLogin = 1
        const val managerLogin = 2
        const val loginMode = "login_mode"
    }

    override fun onInit() {
        val intent = intent
        mode = intent.getIntExtra(loginMode, -1)
        bt_login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val username = et_username.editableText
        val password = et_password.editableText
        //todo -> upload username password mode
        if (mode == userLogin) {
            // jump to user
        } else if(mode == managerLogin){
            // jump to manager
        }
        //ceshi
        Navigation.jumpWithBundle(this, Router.MEAL, Bundle().apply {
            putInt("ceshi", 1)
        })

    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_login
    }

}