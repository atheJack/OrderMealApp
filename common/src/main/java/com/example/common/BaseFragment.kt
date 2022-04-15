package com.example.common
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment<T: BaseViewModel> : Fragment() {

    protected lateinit var viewModel: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(createVm()::class.java)
        onInit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getContentLayoutId(), container, false)
    }

    abstract fun createVm(): T

    abstract fun onInit()

    abstract fun getContentLayoutId(): Int
}