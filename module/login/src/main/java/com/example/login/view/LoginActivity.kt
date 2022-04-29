package com.example.login.view
import com.example.common.BaseActivity
import com.example.common.database.DbManager
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.common.sharepreference.SharedPreferenceConst
import com.example.common.sharepreference.SharedPreferenceUtil
import com.example.common.util.ToastUtil
import com.example.login.R
import com.example.common.model.User
import com.example.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginViewModel>() {

    override fun createVm(): LoginViewModel {
        return LoginViewModel()
    }

    override fun onInit() {
        initView()
        initObserver()
    }

    private fun initView() {
        bt_login.setOnClickListener {
            login()
        }
    }

    private fun initObserver() {
        viewModel.loginData.observe(this, {
            if(it.code == 200) {
                SharedPreferenceUtil.putIntAsync(this, SharedPreferenceConst.LOGIN_STATE,
                    SharedPreferenceConst.LoginStateValue.HAS_LOGIN)
                Thread {
                    DbManager.getInstance().getDb(this).userDao().deleteAll()
                    DbManager.getInstance().getDb(this).userDao().insert(it.data)
                }.start()
                if (it.data.isManager) {
                    SharedPreferenceUtil.putIntAsync(this, SharedPreferenceConst.LOGIN_MODE, SharedPreferenceConst.LoginModeValue.MANAGER_LOGIN)
                    Navigation.jump(this, Router.MAIN_MANAGER)
                } else {
                    SharedPreferenceUtil.putIntAsync(this, SharedPreferenceConst.LOGIN_MODE, SharedPreferenceConst.LoginModeValue.USER_LOGIN)
                    Navigation.jump(this, Router.MAIN)
                }
            } else {
                ToastUtil.showToastShort(this, it.message)
            }
        })
    }

    private fun login() {
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        if (username == "" || password == "") {
            ToastUtil.showToastShort(this, "用户名或密码为空！")
        } else {
            viewModel.login(User(name = username, password = password))
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_login
    }

}