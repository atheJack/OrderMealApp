package com.example.common
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.common.util.ActivityUtil
import com.example.common.util.StatusBarUtil

abstract class BaseActivity<T: BaseViewModel> : AppCompatActivity() {

    lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentLayoutId())
        viewModel = ViewModelProviders.of(this).get(createVm()::class.java)
        //StatusBarUtil.setWindowStatusBarColor(this, R.color.white)
        onInit()
        ActivityUtil.pushActivity(this)
    }

    override fun onDestroy() {
        ActivityUtil.popActivity(this)
        super.onDestroy()
    }

    abstract fun createVm(): T

    abstract fun onInit()

    abstract fun getContentLayoutId(): Int
}