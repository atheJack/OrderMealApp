package com.example.common
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<T: BaseViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentLayoutId())
        viewModel = ViewModelProviders.of(this).get(createVm()::class.java)
        onInit()
    }

    abstract fun createVm(): T

    abstract fun onInit()

    abstract fun getContentLayoutId(): Int
}