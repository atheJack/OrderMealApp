package com.example.login.view
import com.example.common.BaseActivity
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.common.sharepreference.SharedPreferenceConst
import com.example.common.sharepreference.SharedPreferenceUtil
import com.example.common.util.ToastUtil
import com.example.login.R
import com.example.common.model.User
import com.example.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.et_password
import kotlinx.android.synthetic.main.activity_login.et_username
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity<LoginViewModel>() {

    override fun onInit() {
        initView()
        initObserver()
    }

    private fun initView() {
        bt_login.setOnClickListener {
            register()
        }
        tv_registered.setOnClickListener {
            Navigation.jump(this, Router.LOGIN)
        }
    }

    private fun initObserver() {
        viewModel.registerData.observe(this,{
            if (it.code == 200) {
                SharedPreferenceUtil.putIntAsync(this, SharedPreferenceConst.REGISTER_STATE,
                SharedPreferenceConst.RegisterStateValue.HAS_REGISTER)
                Navigation.jump(this, Router.LOGIN)
            }
        })
    }

    private fun register() {
        val name = et_username.text.toString()
        val password = et_password.text.toString()
        val passwordConfirm = et_password_confirm.text.toString()
        if (name == "" || password == "" || passwordConfirm == "") {
            ToastUtil.showToastShort(this, "用户名或密码为空！")
        } else if(password != passwordConfirm){
            ToastUtil.showToastShort(this, "密码与确认密码不一致！")
        } else {
            viewModel.register(User(name = name, password = password, isManager = false))
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun createVm(): LoginViewModel {
        return LoginViewModel()
    }
}