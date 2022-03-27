package com.example.common.router

import android.content.Context
import android.content.Intent
import android.os.Bundle

object Navigation {

    const val EXTRA_DATA = "extra_data"

    fun jump(context: Context, page: PageInfo) {
        val intent = Intent(context, Class.forName(page.pageName))
        context.startActivity(intent)
    }

    fun jumpWithBundle(context: Context, page: PageInfo, bundle: Bundle) {
        val intent = Intent(context, Class.forName(page.pageName))
        intent.putExtra(EXTRA_DATA, bundle)
        context.startActivity(intent)
    }
}