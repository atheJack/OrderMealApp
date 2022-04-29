package com.example.order.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.BaseActivity
import com.example.common.database.DbManager
import com.example.common.model.Food
import com.example.common.model.Order
import com.example.common.model.User
import com.example.common.recyclelist.CommonItemClickListener
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.common.util.ToastUtil
import com.example.order.R
import com.example.order.recyle.MyOrderListAdapter
import com.example.order.viewmodel.MyOrderViewModel
import kotlinx.android.synthetic.main.activity_my_order.*
import retrofit2.http.Multipart

class MyOrderActivity: BaseActivity<MyOrderViewModel>() {
    private var adapter: MyOrderListAdapter? = null
    override fun createVm(): MyOrderViewModel{
        return MyOrderViewModel()
    }

    override fun onInit() {
        initView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.orderData.observe(this, {
            if (it.code == 200) {
                if (adapter == null) {
                    adapter = MyOrderListAdapter(this, it.data, object: CommonItemClickListener<Order>{
                        override fun onClick(itemView: View, data: List<Order>, position: Int) {
                            itemView.setOnClickListener {
                                Navigation.jumpWithBundle(this@MyOrderActivity, Router.ORDER_DETAIL, Bundle().apply {
                                    val foodList = data[position].foodList as ArrayList<Food>
                                    putSerializable("food_list", foodList)
                                    putInt("launch_mode", 2)
                                    putFloat("total_price", data[position].totalPrice)
                                })
                            }
                        }
                    })
                    rv_order_list.layoutManager = LinearLayoutManager(this)
                    rv_order_list.adapter = adapter
                } else {
                    adapter!!.data = it.data
                    adapter!!.notifyDataSetChanged()
                }
            } else {
                ToastUtil.showToastShort(this, it.message)
            }
        })
    }

    private fun initView() {
        Thread{
            val list = DbManager.getInstance().getDb(this).userDao().getAll()
            if(list.isNotEmpty()) {
                viewModel.getOrderList(list[0])
            } else {
                viewModel.getOrderList(User())
            }
        }.start()
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_my_order
    }

}