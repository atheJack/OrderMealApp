package com.example.manager.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.common.BaseActivity
import com.example.common.model.Food
import com.example.common.util.FileUtil
import com.example.common.util.ToastUtil
import com.example.manager.R
import com.example.manager.viewmodel.ManagerViewModel
import kotlinx.android.synthetic.main.activity_add_food.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class AddFoodActivity: BaseActivity<ManagerViewModel>() {
    private var file: File? = null
    override fun createVm(): ManagerViewModel {
        return ManagerViewModel()
    }

    override fun onInit() {
        initView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.foodAddImgUrl.observe(this, {
            if (it.code == 200) {
                commit(it.data)
            } else {
                ToastUtil.showToastLong(this, it.message)
            }
        })
    }

    private fun initView() {
        bt_commit.setOnClickListener {
            if (file == null) {
                commit("http://xilelqs.top/img/yxrs.png")
            } else {
                val foodName = et_food_name.text.toString() + ".png"
                Thread{
                    val newFile = FileUtil.saveAndCompressBitmap(
                        this,
                        BitmapFactory.decodeFile(file!!.absolutePath),
                        foodName
                    )
                    Handler(mainLooper).post{
                        viewModel.uploadImg(getPart(newFile!!))
                    }
                }.start()
            }
        }
        iv_back.setOnClickListener {
            onBackPressed()
        }
        bt_change_img.setOnClickListener {
            checkWritePermission()
        }
    }

    companion object {
        const val REQUEST_IMAGE_GET = 1
    }

    private fun checkWritePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        } else {
            selectImage()
        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET)
        }
    }

    private fun getPart(file: File): MultipartBody.Part {
        val requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        val body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        return body
    }

    private fun commit(imgUrl: String) {
        val name = et_food_name.text.toString()
        val price = et_food_price.text.toString().toFloat()
        val food = Food()
        food.name = name
        food.price = price
        food.imgUrl = imgUrl
        viewModel.addFood(food)
        val intent = Intent()
        intent.putExtra("food_add_finish", Bundle().apply {
            putSerializable("food", food)
        })
        setResult(RESULT_OK, intent)
        onBackPressed()
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_add_food
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FoodEditActivity.REQUEST_IMAGE_GET && resultCode == Activity.RESULT_OK) {
            val fullPhotoUri: Uri? = data?.data
            Thread {
                val newFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    fullPhotoUri?.let {
                        FileUtil.uriToFileQ(this, it)
                    }
                } else {
                    fullPhotoUri?.let {
                        FileUtil.uriToFile(this, it)
                    }
                }
                if (newFile != null) {
                    file = newFile
                    val bitmap = BitmapFactory.decodeFile(newFile.absolutePath)
                    Handler(Looper.getMainLooper()).post {
                        iv_food_img.setImageBitmap(bitmap)
                    }
                }
            }.start()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectImage()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}