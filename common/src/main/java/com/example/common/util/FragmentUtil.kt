package com.example.common.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object FragmentUtil {

    fun addFragment(activity: FragmentActivity, fragment: Fragment,
                    containerId: Int, backStackName: String) {
        activity.supportFragmentManager
            .beginTransaction()
            .addToBackStack(backStackName)
            .replace(containerId, fragment)
            .commit()
    }

    fun hideFragment(activity: FragmentActivity, fragment: Fragment) {
        activity.supportFragmentManager
            .beginTransaction()
            .hide(fragment)
            .commit()
    }

    fun showFragment(activity: FragmentActivity, fragment: Fragment) {
        activity.supportFragmentManager
            .beginTransaction()
            .show(fragment)
            .commit()
    }
}