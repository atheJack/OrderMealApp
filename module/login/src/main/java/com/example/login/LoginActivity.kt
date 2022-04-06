package com.example.login

import android.os.Bundle
import android.util.Log
import com.example.common.BaseActivity
import com.example.common.network.CommonResponse
import com.example.common.network.NetworkManager
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.login.model.Test
import com.example.login.model.User
import com.example.login.repo.LoginApi
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {

    private var mode = -1

    companion object{
        const val userLogin = 1
        const val managerLogin = 2
        const val loginMode = "login_mode"
    }

    override fun onInit() {
        val intent = intent
        mode = intent.getIntExtra(loginMode, -1)
        bt_login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val username = et_username.editableText
        val password = et_password.editableText
        //todo -> upload username password mode
        if (mode == userLogin) {
            // jump to user
        } else if(mode == managerLogin){
            // jump to manager
        }
        //testNav()
        //testNetwork()
        testRegister()
        //testRegister2()
    }

    fun testNav() {
        Navigation.jumpWithBundle(this, Router.MEAL, Bundle().apply {
            putString("ceshi", "lqs lqs lqs")
        })
    }

    private fun testRegister() {
        val api = NetworkManager.getApi(LoginApi::class.java)
        val call = api.register(User(100,"jack", "1234", false))
        call.enqueue(object : Callback<CommonResponse<User>>{
            override fun onResponse(
                call: Call<CommonResponse<User>>?,
                response: Response<CommonResponse<User>>?
            ) {
                Log.d("lqs", response?.body()?.data.toString())
            }

            override fun onFailure(call: Call<CommonResponse<User>>?, t: Throwable?) {
                Log.d("lqs", "register fail${t.toString()}")
            }
        })
    }

    private fun testRegister2() {
        val api = NetworkManager.getApi(LoginApi::class.java)
        val call = api.register2("jack", "1234", false)
        call.enqueue(object : Callback<CommonResponse<User>>{
            override fun onResponse(
                call: Call<CommonResponse<User>>?,
                response: Response<CommonResponse<User>>?
            ) {
                Log.d("lqs", response?.body()?.data.toString())
            }

            override fun onFailure(call: Call<CommonResponse<User>>?, t: Throwable?) {
                Log.d("lqs", "register fail${t.toString()}")
            }
        })
    }

    fun testNetwork() {
        val api = NetworkManager.getApi(LoginApi::class.java)
        val call = api.getTest()
        call.enqueue(object : Callback<CommonResponse<List<Test>>>{
            override fun onFailure(call: Call<CommonResponse<List<Test>>>?, t: Throwable?) {
                t?.message?.let { Log.d("lqs", it) }
            }

            override fun onResponse(
                call: Call<CommonResponse<List<Test>>>?,
                response: Response<CommonResponse<List<Test>>>?
            ) {
                Log.d("lqs", response?.body().toString())
            }

        })
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_login
    }

}