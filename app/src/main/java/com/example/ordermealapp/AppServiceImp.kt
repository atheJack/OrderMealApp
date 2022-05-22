package com.example.ordermealapp

import android.app.Application
import com.example.app_api.IAppService
import com.example.common.database.DbManager
import com.example.common.util.LogUtil
import com.tencent.android.tpush.XGPushManager

object AppServiceImp: IAppService {
    override fun getApplicationContext(): Application {
        return MyApplication.getInstance()
    }

    override fun addXGPushNotifyCallback(block: () -> Unit) {
        XGPushManager.setNotifactionCallback {
            val userList = DbManager.getInstance().getDb(getApplicationContext()).userDao().getAll()
            if(!userList.isNullOrEmpty()) {
                val user = userList[0]
                if(user.isManager) {
                    it.doNotify()
                }
            }
            block.invoke()
            LogUtil.d("来订单啦！")
        }
    }

}