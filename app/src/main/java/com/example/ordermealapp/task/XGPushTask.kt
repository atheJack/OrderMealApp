package com.example.ordermealapp.task

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.common.database.DbManager
import com.example.common.util.AppUtil
import com.example.common.util.LogUtil
import com.example.ordermealapp.AppServiceImp
import com.tencent.android.tpush.*

object XGPushTask: ITask {

    override fun excute(context: Context) {
        XGPushConfig.enableDebug(context, true)
        XGPushManager.registerPush(context, object : XGIOperateCallback {
            override fun onSuccess(data: Any, flag: Int) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：$data")
            }

            override fun onFail(data: Any, errCode: Int, msg: String) {
                Log.d("TPush", "注册失败，错误码：$errCode,错误信息：$msg")
            }
        })
        XGPushManager.setNotifactionCallback {
            //这里设置默认的是因为，这个回调可以自行控制是否显示通知
            val userList = DbManager.getInstance().getDb(AppServiceImp.getApplicationContext()).userDao().getAll()
            if(!userList.isNullOrEmpty()) {
                val user = userList[0]
                if(user.isManager) {
                    it.doNotify()
                }
            }
            LogUtil.d("默认的通知回调-来订单啦！")
        }
    }

}