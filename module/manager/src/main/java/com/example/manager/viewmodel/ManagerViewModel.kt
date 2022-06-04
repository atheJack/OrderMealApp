package com.example.manager.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.common.BaseViewModel
import com.example.common.ModuleServiceLoader
import com.example.common.model.Food
import com.example.common.network.CommonResponse
import com.example.common.network.NetworkManager
import com.example.common.util.LogUtil
import com.example.manager.repo.ManagerApi
import com.example.meal_api.IMealService
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManagerViewModel: BaseViewModel() {
    val foodListData: MutableLiveData<CommonResponse<List<Food>>> = MutableLiveData()
    var foodTypeData: MutableLiveData<CommonResponse<List<Int>>> = MutableLiveData()
    val foodEditImgUrl: MutableLiveData<CommonResponse<String>> = MutableLiveData()
    val foodAddImgUrl: MutableLiveData<CommonResponse<String>> = MutableLiveData()
    val foodEditFinishData: MutableLiveData<CommonResponse<Food>> = MutableLiveData()
    private val api = NetworkManager.getApi(ManagerApi::class.java)

    fun getFoodList() {
        api.getFoodList().enqueue(object : Callback<CommonResponse<List<Food>>>{
            override fun onResponse(
                call: Call<CommonResponse<List<Food>>>?,
                response: Response<CommonResponse<List<Food>>>?
            ) {
                foodListData.postValue(response?.body())
                LogUtil.d("finish get foodList")
            }

            override fun onFailure(call: Call<CommonResponse<List<Food>>>?, t: Throwable?) {

            }

        })
    }

    fun getFoodTypeList() {
        api.getFoodTypeList().enqueue(object : Callback<CommonResponse<List<Int>>> {
            override fun onResponse(
                call: Call<CommonResponse<List<Int>>>?,
                response: Response<CommonResponse<List<Int>>>?
            ) {
                foodTypeData.postValue(response?.body())
            }

            override fun onFailure(call: Call<CommonResponse<List<Int>>>?, t: Throwable?) {

            }
        })
    }

    fun addFood(food: Food) {
        api.addFood(food).enqueue(object : Callback<CommonResponse<Food>> {
            override fun onResponse(
                call: Call<CommonResponse<Food>>?,
                response: Response<CommonResponse<Food>>?
            ) {

            }

            override fun onFailure(call: Call<CommonResponse<Food>>?, t: Throwable?) {

            }
        })
    }

    fun delFood(food: Food) {
        api.delFood(food).enqueue(object : Callback<CommonResponse<Food>> {
            override fun onResponse(
                call: Call<CommonResponse<Food>>?,
                response: Response<CommonResponse<Food>>?
            ) {
                LogUtil.d(response?.body().toString())
            }

            override fun onFailure(call: Call<CommonResponse<Food>>?, t: Throwable?) {
                LogUtil.d(t.toString())
            }
        })
    }

    fun updateFood(food: Food) {
        api.updateFood(food).enqueue(object : Callback<CommonResponse<Food>> {
            override fun onResponse(
                call: Call<CommonResponse<Food>>?,
                response: Response<CommonResponse<Food>>?
            ) {
                foodEditFinishData.postValue(response?.body())
                LogUtil.d("success:"+response?.body().toString())
            }

            override fun onFailure(call: Call<CommonResponse<Food>>?, t: Throwable?) {
                LogUtil.d("fail:"+t.toString())
            }
        })
    }

    fun uploadImg(multipart: MultipartBody.Part) {
        api.uploadImg(multipart).enqueue(object : Callback<CommonResponse<String>>{
            override fun onResponse(
                call: Call<CommonResponse<String>>?,
                response: Response<CommonResponse<String>>?
            ) {
                foodEditImgUrl.postValue(response?.body())
                foodAddImgUrl.postValue(response?.body())
            }

            override fun onFailure(call: Call<CommonResponse<String>>?, t: Throwable?) {
                LogUtil.d(t?.message.toString())
            }

        })
    }

}