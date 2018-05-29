package com.example.alexm.imageuploader.Interfaces

import android.content.Context
import android.graphics.Bitmap
import io.reactivex.Observable

public interface Contract{
    interface IView{
        fun displayImages(bitmaps: ArrayList<Bitmap> )
    }
    interface IPresenter{
        fun subscribe(view:IView)
        fun unsubscribe()
        fun useImages(context: Context)
    }
    interface ILogic{
        fun uploadImage(context: Context):Observable<ArrayList<Bitmap>>
    }

}