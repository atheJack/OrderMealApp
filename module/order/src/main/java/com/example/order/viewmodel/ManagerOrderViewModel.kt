package com.example.order.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.common.BaseViewModel
import com.example.common.ModuleServiceLoader
import com.example.common.model.Food
import com.example.common.model.Order
import com.example.common.network.CommonResponse
import com.example.common.network.NetworkManager
import com.example.meal_api.IMealService
import com.example.order.repo.OrderApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class ManagerOrderViewModel: BaseViewModel() {

    val orderData: MutableLiveData<CommonResponse<List<Order>>> = MutableLiveData()
    private val api = NetworkManager.getApi(OrderApi::class.java)

    fun getOrderList() {
        api.getAllOrder().enqueue(object : Callback<CommonResponse<List<Order>>> {
            override fun onFailure(call: Call<CommonResponse<List<Order>>>?, t: Throwable?) {

            }

            override fun onResponse(
                call: Call<CommonResponse<List<Order>>>?,
                response: Response<CommonResponse<List<Order>>>?
            ) {
                orderData.postValue(response?.body())
            }

        })
    }

    fun updateOrderState(order: Order) {
        api.updateOrderState(order).enqueue(object : Callback<CommonResponse<Order>>{
            override fun onResponse(
                call: Call<CommonResponse<Order>>?,
                response: Response<CommonResponse<Order>>?
            ) {

            }

            override fun onFailure(call: Call<CommonResponse<Order>>?, t: Throwable?) {

            }

        })
    }

}