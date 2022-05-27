package com.example.common.util

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.FileUtils
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi
import java.io.*
import java.nio.channels.FileChannel
import kotlin.random.Random


object FileUtil {

    private const val SIZE_3M = 1024*1024*3
    private const val SIZE_4M = 1024*1024*4
    private const val SIZE_5M = 1024*1024*5
    private const val SIZE_6M = 1024*1024*6

    fun drawableToFile(): File {
        val file = File("")
        return file
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun uriToFileQ(context: Context, uri: Uri): File? =
        if (uri.scheme == ContentResolver.SCHEME_FILE)
            File(requireNotNull(uri.path))
        else if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
            val contentResolver = context.contentResolver
            val displayName = "${System.currentTimeMillis()}${Random.nextInt(0, 9999)}.${
                MimeTypeMap.getSingleton()
                    .getExtensionFromMimeType(contentResolver.getType(uri))}"
            val ios = contentResolver.openInputStream(uri)
            if (ios != null) {
                File("${context.cacheDir.absolutePath}/$displayName")
                    .apply {
                        val fos = FileOutputStream(this)
                        FileUtils.copy(ios, fos)
                        fos.close()
                        ios.close()
                    }
            } else null
        } else null

    fun uriToFile(context: Context, uri: Uri): File? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = context.contentResolver.query(
            uri,
            filePathColumn, null, null, null
        )
        cursor?.let {
            it.moveToFirst()
            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
            val path = cursor.getString(columnIndex)
            return File(path)
        }
        return null
    }

    fun getImageCompressOption(image: Bitmap): Int {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val size = baos.toByteArray().size
        LogUtil.d("originalBitmapSize:" + image.byteCount.toString())
        LogUtil.d("orginalSize:" + size.toString())
        var options = 100
        when {
            size > SIZE_6M -> {
                options = 50
            }
            size > SIZE_5M -> {
                options = 60
            }
            size > SIZE_4M -> {
                options = 70
            }
            size > SIZE_3M ->{
                options = 80
            }
        }
        return options
    }

    private fun compressScale(image: Bitmap): Bitmap? {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 80, baos)
        var isBm = ByteArrayInputStream(baos.toByteArray())
        val newOpts = BitmapFactory.Options()
        newOpts.inJustDecodeBounds = true
        var bitmap = BitmapFactory.decodeStream(isBm, null, newOpts)
        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth
        val h = newOpts.outHeight
        val hh = 512f
        val ww = 512f
        var be = 1
        if (w > h && w > ww) {
            be = (newOpts.outWidth / ww).toInt()
        } else if (w < h && h > hh) {
            be = (newOpts.outHeight / hh).toInt()
        }
        if (be <= 0) be = 1
        newOpts.inSampleSize = be
        isBm = ByteArrayInputStream(baos.toByteArray())
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts)
        return bitmap
    }

    fun saveBitmapToFile(context: Context, bm: Bitmap, fileName: String): File? {
        val savePicPath = context.filesDir.absolutePath
        val realPath = "/$savePicPath/lqs/"
        var myCaptureFile: File? = null
        try {
            val subFolder = realPath
            val folder = File(subFolder)
            if (!folder.exists()) folder.mkdirs()
            myCaptureFile = File(subFolder, fileName)
            myCaptureFile.createNewFile()
            val bos = BufferedOutputStream(FileOutputStream(myCaptureFile))
            val bmNew = compressScale(bm)
            bmNew?.compress(Bitmap.CompressFormat.PNG, getImageCompressOption(bmNew), bos)
            LogUtil.d("finalFileSize:" + myCaptureFile.length())
            LogUtil.d("finalSize:" + bm.byteCount.toString())
            bos.flush()
            bos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return myCaptureFile
    }

}