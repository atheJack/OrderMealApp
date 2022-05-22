package com.example.order.view
import com.example.common.BaseActivity
import com.example.common.BaseViewModel
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.common.util.ActivityUtil
import com.example.order.R
import kotlinx.android.synthetic.main.activity_order_finish.*

class OrderFinishActivity : BaseActivity<BaseViewModel>() {

    override fun createVm(): BaseViewModel {
        return BaseViewModel()
    }

    override fun onInit() {
        getData()
        bt_go_order.setOnClickListener {
            ActivityUtil.backToMain()
            Navigation.jump(this, Router.MY_ORDER)
        }
    }

    private fun getData() {
        val orderId = Navigation.getBundle(intent)?.getInt("order_id")
        tv_order_num.text = "单号：${String.format("%03d", orderId)}"
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_order_finish
    }
}