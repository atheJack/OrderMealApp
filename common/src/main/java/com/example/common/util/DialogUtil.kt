package com.example.common.util

import android.app.ProgressDialog
import android.content.Context

object DialogUtil {

    fun getLoadingDialog(context: Context, title: String, msg: String, cancelable: Boolean): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.apply {
            setTitle(title)
            setMessage(msg)
            isIndeterminate = true
            setCancelable(cancelable)
        }
        return progressDialog
    }
}