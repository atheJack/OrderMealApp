package com.example.login.view
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.common.BaseActivity
import com.example.common.BaseViewModel
import com.example.login.R
import kotlinx.android.synthetic.main.activity_user_choose.*
class UserChooseActivity: BaseActivity<BaseViewModel>() {

    companion object{
        const val userLogin = 1
        const val managerLogin = 2
        const val loginMode = "login_mode"
    }

    override fun onInit() {
        iv_user.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java )
            intent.putExtra(loginMode, userLogin)
            startActivity(intent)
            finish()
        }
        iv_manager.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java )
            intent.putExtra(loginMode, managerLogin)
            startActivity(intent)
            finish()
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_user_choose
    }

    override fun createVm(): BaseViewModel {
        TODO("Not yet implemented")
    }

}