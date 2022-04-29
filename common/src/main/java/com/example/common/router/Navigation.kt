package com.example.common.router

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

object Navigation {

    private const val EXTRA_DATA = "extra_data"

    fun jump(context: Context, page: PageInfo) {
        val intent = Intent(context, Class.forName(page.pageName))
        context.startActivity(intent)
    }

    fun jumpWithBundle(context: Context, page: PageInfo, bundle: Bundle) {
        val intent = Intent(context, Class.forName(page.pageName))
        intent.putExtra(EXTRA_DATA, bundle)
        context.startActivity(intent)
    }

    fun jumpForResult(activity: FragmentActivity, page: PageInfo, requestCode: Int) {
        val intent = Intent(activity, Class.forName(page.pageName))
        activity.startActivityForResult(intent, requestCode)
    }

    fun jumpWithBundleForResult(activity: FragmentActivity, page: PageInfo, requestCode: Int, bundle: Bundle) {
        val intent = Intent(activity, Class.forName(page.pageName))
        intent.putExtra(EXTRA_DATA, bundle)
        activity.startActivityForResult(intent, requestCode)
    }

    fun getBundle(intent: Intent): Bundle? {
        return intent.getBundleExtra(EXTRA_DATA)
    }
}