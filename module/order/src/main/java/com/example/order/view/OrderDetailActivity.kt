package com.example.order.view
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.common.BaseActivity
import com.example.common.BaseViewModel
import com.example.common.ModuleServiceLoader
import com.example.common.database.DbManager
import com.example.common.model.Food
import com.example.common.model.Order
import com.example.common.model.User
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.common.util.LogUtil
import com.example.meal_api.IMealService
import com.example.order.R
import com.example.order.viewmodel.MyOrderViewModel
import kotlinx.android.synthetic.main.activity_order_detail.*

class OrderDetailActivity : BaseActivity<MyOrderViewModel>() {

    private var listData: List<Any> = ArrayList()
    private var totalPrice: Float = 0f
    private var launchMode: Int = MODE_CONFIRM
    private var totalNum: Int = 0
    private var order: Order = Order()

    companion object{
        const val MODE_CONFIRM = 1
        const val MODE_DETAIL = 2
    }

    override fun createVm(): MyOrderViewModel {
        return MyOrderViewModel()
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_order_detail
    }

    override fun onInit() {
        getData()
        iv_back.setOnClickListener{
            onBackPressed()
        }
        tv_title.setOnClickListener {
            val obj = ModuleServiceLoader.instance.get(IMealService::class.java)?.getFoodObject()
            LogUtil.d(obj.toString())
        }
        rv_order_food_list.adapter =
            ModuleServiceLoader.instance.get(IMealService::class.java)?.getAdapter(this, listData) as RecyclerView.Adapter<*>?
        rv_order_food_list.layoutManager = LinearLayoutManager(this)
        tv_go_pay.setOnClickListener {
            generateOrder()
            Navigation.jump(this, Router.ORDER_FINISH)
        }
        tv_total_num.text = "￥${totalPrice}"
        tv_sum_price.text = "￥${totalPrice}"
    }

    private fun getData() {
        val bundle = Navigation.getBundle(intent)
        bundle?.let {
            listData = it.getSerializable("food_list") as List<Any>
            totalPrice = it.getFloat("total_price")
            totalNum = it.getInt("total_num")
            launchMode = it.getInt("launch_mode")
        }
        if(launchMode == MODE_DETAIL) {
            ll_order_confirm_view.visibility = View.GONE
            tv_title.text = "订单详情"
        }

    }

    private fun generateOrder() {
        Thread{
            order.state = 1
            order.date = System.currentTimeMillis()
            order.id = 1
            val list = DbManager.getInstance().getDb(this).userDao().getAll()
            if(list.isNotEmpty()) {
                order.username = list[0].name
            } else {
                order.username = ""
            }
            order.totalNum = totalNum
            order.totalPrice = totalPrice
            order.foodList = listData as List<Food>
            viewModel.generateOrder(order)
        }.start()
    }
}