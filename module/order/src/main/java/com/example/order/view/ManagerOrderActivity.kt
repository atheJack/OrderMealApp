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
import com.example.common.util.ToastUtil
import com.example.order.R
import com.example.order.recyle.MyOrderListAdapter
import com.example.order.viewmodel.ManagerOrderViewModel
import kotlinx.android.synthetic.main.activity_manager_order.*
import kotlinx.android.synthetic.main.activity_my_order.iv_back
import kotlinx.android.synthetic.main.activity_my_order.rv_order_list

class ManagerOrderActivity: BaseActivity<ManagerOrderViewModel>() {
    private var adapter: MyOrderListAdapter? = null
    private var orderStates = arrayOf("待处理", "处理中", "已完成")
    private var orderStatesCode = arrayOf(1, 2, 3)
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
                } else {
                    adapter!!.data = it.data
                    adapter!!.notifyDataSetChanged()
                }
            } else {
                ToastUtil.showToastShort(this, it.message)
            }
        })
    }

    private fun showChangeStateDialog(order: Order) {
        AlertDialog.Builder(this@ManagerOrderActivity).apply {
            setTitle("订单状态变更")
            setItems(orderStates) { dialog, which ->
                order.state = orderStatesCode[which]
                adapter?.notifyDataSetChanged()
                viewModel.updateOrderState(order)
                dialog?.cancel()
            }
        }.show()
    }

    private fun initView() {
        viewModel.getOrderList()
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }


    override fun getContentLayoutId(): Int {
        return R.layout.activity_manager_order
    }
}