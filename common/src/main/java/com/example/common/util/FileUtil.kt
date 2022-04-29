package com.example.common.util

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.FileUtils
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.channels.FileChannel
import kotlin.random.Random


object FileUtil {
    fun drawableToFile(): File {
        val file = File("")
        return file
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun uriToFileQ(context: Context, uri: Uri): File? =
        if (uri.scheme == ContentResolver.SCHEME_FILE)
            File(requireNotNull(uri.path))
        else if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
            //把文件保存到沙盒
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
        ) //从系统表中查询指定Uri对应的照片
        cursor?.let {
            it.moveToFirst()
            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
            val path = cursor.getString(columnIndex) //获取照片路径
            return File(path)
        }
        return null
    }

    //保存方法
    fun saveAndCompressBitmap(context: Context, bm: Bitmap, fileName: String): File? {
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
            bm.compress(Bitmap.CompressFormat.PNG, 90, bos) //质量压缩到90
            bos.flush()
            bos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return myCaptureFile
    }

    fun copyFileUsingFileChannels(source: File, dest: File) {
        var inputChannel: FileChannel? = null
        var outputChannel: FileChannel? = null
        try {
            inputChannel = FileInputStream(source).getChannel()
            outputChannel = FileOutputStream(dest).channel
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size())
        } finally {
            inputChannel?.close()
            outputChannel?.close()
        }
    }
}