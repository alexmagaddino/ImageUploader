package com.example.alexm.imageuploader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.alexm.imageuploader.Interfaces.Contract
import com.snatik.storage.Storage
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

class Logic:Contract.ILogic {

    override fun uploadImage(context: Context): Observable<ArrayList<Bitmap>> {
        return Observable.defer({
            var arrayList = ArrayList<Bitmap> ()
            try{
                val storage = Storage(context)
                val files = storage.getFiles(storage.externalStorageDirectory + "${File.separator}MyFolder")
                files.forEach{
                    val filePath = it.path
                    val bitmap = BitmapFactory.decodeFile(filePath)
                    arrayList.add(bitmap)
                }
            }catch(e: Exception){
                e.printStackTrace()
            }
            return@defer Observable.just(arrayList)
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}