package com.example.login.viewmodel
import androidx.lifecycle.MutableLiveData
import com.example.common.BaseViewModel
import com.example.common.network.CommonResponse
import com.example.common.network.NetworkManager
import com.example.common.model.User
import com.example.login.repo.LoginApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: BaseViewModel() {

    val registerData: MutableLiveData<CommonResponse<User>> = MutableLiveData()
    val loginData: MutableLiveData<CommonResponse<User>> = MutableLiveData()
    private val api = NetworkManager.getApi(LoginApi::class.java)

    fun register(user: User) {
        val call = api.register(user)
        call.enqueue(object : Callback<CommonResponse<User>> {
            override fun onResponse(
                call: Call<CommonResponse<User>>?,
                response: Response<CommonResponse<User>>?
            ) {
                registerData.postValue(response?.body())
            }

            override fun onFailure(call: Call<CommonResponse<User>>?, t: Throwable?) {

            }
        })
    }

    fun login(user: User) {
        val call = api.login(user)
        call.enqueue(object : Callback<CommonResponse<User>> {
            override fun onResponse(
                call: Call<CommonResponse<User>>?,
                response: Response<CommonResponse<User>>?
            ) {
                loginData.postValue(response?.body())
            }

            override fun onFailure(call: Call<CommonResponse<User>>?, t: Throwable?) {

            }
        })
    }
}