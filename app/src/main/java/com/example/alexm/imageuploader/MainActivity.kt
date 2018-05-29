package com.example.alexm.imageuploader

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.snatik.storage.Storage
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val PERMISSION_REQUEST_CODE:Int = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        upload.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            upload -> checkPermission()
        }
    }

    private fun checkPermission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager
                        .PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE);
        }else
            uploadImage()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_REQUEST_CODE ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    uploadImage()
                else
                    Toast.makeText(this, "Unauthorized to load the image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadImage() {
        val storage = Storage(this)
        val files = storage.getFiles(storage.externalStorageDirectory + "${File.separator}MyFolder")
        files.forEach{
            val filePath = it.path
            val bitmap = BitmapFactory.decodeFile(filePath)
            image.setImageBitmap(bitmap)
        }
        Toast.makeText(this, "Yeahhh", Toast.LENGTH_SHORT).show()
    }
}
