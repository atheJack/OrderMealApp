package com.example.common.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.example.common.sharepreference.SharedPreferenceConst
import com.example.common.sharepreference.SharedPreferenceUtil
import java.security.MessageDigest

object GlideUtil {

    fun loadUrlNoCache(context: Context, url: String, target: ImageView) {
        Glide
            .with(context)
            .load(url)
            .diskCacheStrategy( DiskCacheStrategy.NONE )//禁用磁盘缓存
            .skipMemoryCache( true )//跳过内存缓存
            .into(target)
    }

    fun loadUrlWithSign(context: Context, url: String, target: ImageView) {
        val sign = SharedPreferenceUtil.getLong(context, SharedPreferenceConst.GLIDE_SIGN, System.currentTimeMillis())
        Glide
            .with(context)
            .load(url)
            .signature(ObjectKey(sign))
            .into(target)
    }

    /**
     * 普通的load，带缓存
     */
    fun loadUrl(context: Context, url: String, target: ImageView) {
        Glide
            .with(context)
            .load(url)
            .into(target)
    }

    fun clearCache(context: Context) {
        Thread{
            Glide.get(context).clearDiskCache()
            Handler(Looper.getMainLooper()).post {
                Glide.get(context).clearMemory()
            }
        }
    }
}