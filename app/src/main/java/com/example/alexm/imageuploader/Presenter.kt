package com.example.alexm.imageuploader

import android.content.Context
import com.example.alexm.imageuploader.Interfaces.Contract.*

class Presenter: IPresenter {

    private var view: IView? = null
    private var logic = Logic()

    override fun subscribe(view: IView) {
        this.view = view
    }

    override fun unsubscribe() {
        view = null
    }

    override fun useImages(context: Context) {
        logic.uploadImage(context)
                .subscribe({t -> view?.displayImages(t)},{e -> e.printStackTrace()})

    }
}