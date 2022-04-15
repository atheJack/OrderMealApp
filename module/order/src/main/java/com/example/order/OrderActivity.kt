package com.example.order
import com.example.common.BaseActivity
import com.example.common.BaseViewModel
import com.example.common.ModuleServiceLoader
import com.example.common.util.LogUtil
import com.example.meal_api.MealService
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : BaseActivity<BaseViewModel>() {

    override fun createVm(): BaseViewModel {
        return BaseViewModel()
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_order
    }

    override fun onInit() {
        iv_back.setOnClickListener{
            onBackPressed()
        }
        tv_title.setOnClickListener {
            val obj = ModuleServiceLoader.instance.get(MealService::class.java)?.getFoodObject()
            LogUtil.d(obj.toString())
        }
    }
}