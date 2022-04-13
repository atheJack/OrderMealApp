package com.example.common.widget

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText


class DrawableEditText : AppCompatEditText {
    var drawableSize = 20

    constructor(context: Context, attrs: AttributeSet) : super(
        context, attrs
    ) {
        initSize(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        initSize(context, attrs)
    }

    private fun initSize(context: Context, attrs: AttributeSet) {

    }

    override fun setCompoundDrawables(
        left: Drawable?,
        top: Drawable?,
        right: Drawable?,
        bottom: Drawable?
    ) {
        //TODO 大小没有变化？？？
        left?.let {
            drawableSize = 100
            val drawableNew = zoomDrawable(it, drawableSize, drawableSize)
            val w = drawableNew?.intrinsicWidth
            super.setCompoundDrawables(drawableNew, top, right, bottom)
        }
        top?.let {
            drawableSize = 20
            val drawableNew = zoomDrawable(it, drawableSize, drawableSize)
            super.setCompoundDrawables(left, drawableNew, right, bottom)
        }
        right?.let {
            drawableSize = 20
            val drawableNew = zoomDrawable(it, drawableSize, drawableSize)
            super.setCompoundDrawables(left, top, drawableNew, bottom)
        }
        bottom?.let {
            drawableSize = 20
            val drawableNew = zoomDrawable(it, drawableSize, drawableSize)
            super.setCompoundDrawables(left, top, right, drawableNew)
        }
    }

    // 5. Drawable----> Bitmap
    fun drawableToBitmap(drawable: Drawable): Bitmap? {

        // 获取 drawable 长宽
        val width = drawable.intrinsicWidth
        val heigh = drawable.intrinsicHeight
        drawable.setBounds(0, 0, width, heigh)

        // 获取drawable的颜色格式
        val config =
            if (drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565
        // 创建bitmap
        val bitmap = Bitmap.createBitmap(width, heigh, config)
        // 创建bitmap画布
        val canvas = Canvas(bitmap)
        // 将drawable 内容画到画布中
        drawable.draw(canvas)
        return bitmap
    }

    // 8. bitmap ---Drawable
    fun bitmapToDrawable(bitmap: Bitmap?, context: Context): Drawable? {
        return BitmapDrawable(
            context.resources,
            bitmap
        )
    }

    // 9. drawable进行缩放 ---> bitmap 然后比对bitmap进行缩放
    fun zoomDrawable(drawable: Drawable, w: Int, h: Int): Drawable? {
        val width = drawable.intrinsicWidth
        val height = drawable.intrinsicHeight
        // 调用5 中 drawable转换成bitmap
        val oldbmp: Bitmap = drawableToBitmap(drawable)!!
        // 创建操作图片用的Matrix对象
        val matrix = Matrix()
        // 计算缩放比例
        val sx = w.toFloat() / width
        val sy = h.toFloat() / height
        // 设置缩放比例
        matrix.postScale(sx, sy)
        // 建立新的bitmap，其内容是对原bitmap的缩放后的图
        val newbmp = Bitmap.createBitmap(
            oldbmp, 0, 0, width, height,
            matrix, true
        )
        val w = newbmp.width
        return BitmapDrawable(newbmp)
    }
}