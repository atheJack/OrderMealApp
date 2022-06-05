package com.example.order.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.BaseActivity
import com.example.common.model.Food
import com.example.common.model.Order
import com.example.common.recyclelist.CommonItemClickListener
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.common.util.AppUtil
import com.example.common.util.ToastUtil
import com.example.order.R
import com.example.order.recyle.MyOrderListAdapter
import com.example.order.recyle.OrderTypeAdapter
import com.example.order.viewmodel.ManagerOrderViewModel
import kotlinx.android.synthetic.main.activity_manager_order.*
import kotlinx.android.synthetic.main.activity_manager_order.rv_type_list
import kotlinx.android.synthetic.main.activity_my_order.*
import kotlinx.android.synthetic.main.activity_my_order.iv_back
import kotlinx.android.synthetic.main.activity_my_order.rv_order_list

class ManagerOrderActivity: BaseActivity<ManagerOrderViewModel>() {
    private var adapter: MyOrderListAdapter? = null
    private var orderStates = arrayOf("待处理", "处理中", "已完成")
    private var orderStatesCode = arrayOf(1, 2, 3)
    private var typeAdapter: OrderTypeAdapter? = null
    override fun createVm(): ManagerOrderViewModel {
        return ManagerOrderViewModel()
    }

    override fun onInit() {
        initView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.orderData.observe(this, {
            if (it.code == 200) {
                if (adapter == null) {
                    adapter = MyOrderListAdapter(this, it.data, object: CommonItemClickListener<Order> {
                        override fun onClick(itemView: View, data: List<Order>, position: Int) {
                            itemView.setOnClickListener {
                                Navigation.jumpWithBundle(this@ManagerOrderActivity, Router.ORDER_DETAIL, Bundle().apply {
                                    val foodList = data[position].foodList as ArrayList<Food>
                                    putSerializable("food_list", foodList)
                                    putInt("launch_mode", 2)
                                    putFloat("total_price", data[position].totalPrice)
                                })
                            }
                            val changeStateButton = itemView.findViewById<Button>(R.id.bt_order_change_state)
                            changeStateButton.visibility = View.VISIBLE
                            changeStateButton.setOnClickListener {
                                showChangeStateDialog(data[position])
                            }
                        }
                    })
                    rv_order_list.layoutManager = LinearLayoutManager(this)
                    rv_order_list.adapter = adapter
                    showTypeOrderData(typeAdapter!!.selectPosition+1)
                } else {
                    adapter!!.data = it.data
                    adapter!!.notifyDataSetChanged()
                    showTypeOrderData(typeAdapter!!.selectPosition+1)
                }
            } else {
                ToastUtil.showToastShort(this, it.message)
            }
        })
    }

    private fun initTypeAdapter() {
        val list = listOf(1,2,3)
        typeAdapter = OrderTypeAdapter(this, list, 0)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rv_type_list.layoutManager = layoutManager
        rv_type_list.adapter = typeAdapter
        typeAdapter!!.listener = object : CommonItemClickListener<Int>{
            override fun onClick(itemView: View, data: List<Int>, position: Int) {
                itemView.setOnClickListener{
                    typeAdapter!!.selectPosition = position
                    typeAdapter!!.notifyDataSetChanged()
                    showTypeOrderData(data[position])
                }
            }
        }
    }

    private fun showTypeOrderData(type: Int) {
        val orderList = viewModel.orderData.value?.data
        if(!orderList.isNullOrEmpty()) {
            val newOrderList = ArrayList<Order>()
            for (order in orderList) {
                if (order.state == type) {
                    newOrderList.add(order)
                }
            }
            adapter?.let {
                adapter!!.data = newOrderList
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    private fun showChangeStateDialog(order: Order) {
        AlertDialog.Builder(this@ManagerOrderActivity).apply {
            setTitle("订单状态变更")
            setItems(orderStates) { dialog, which ->
                if (order.state != orderStatesCode[which]) {
                    order.state = orderStatesCode[which]
                    viewModel.updateOrderState(order)
                    val dataList = adapter!!.data as ArrayList
                    dataList.remove(order)
                    adapter?.notifyDataSetChanged()
                }
                dialog?.cancel()
            }
        }.show()
    }

    private fun initView() {
        viewModel.getOrderList()
        iv_back.setOnClickListener {
            onBackPressed()
        }
        AppUtil.addXGPushNotifyCallback {
            viewModel.getOrderList()
        }
        initTypeAdapter()
    }


    override fun getContentLayoutId(): Int {
        return R.layout.activity_manager_order
    }
}