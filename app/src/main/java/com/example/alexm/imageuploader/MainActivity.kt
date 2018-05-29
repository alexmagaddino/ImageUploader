package com.example.alexm.imageuploader

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.alexm.imageuploader.Interfaces.Contract.IView

class MainActivity : AppCompatActivity(),IView{

    private val PERMISSION_REQUEST_CODE:Int = 1000
    private val MESSAGE_ALL_GREEN: String = "Yeahhh"
    private val MESSAGE_HOUSTON_WHE_HAVE_A_PROBLEM: String = "Unauthorized to load the image"
    private val presenter = Presenter()
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         checkPermission()
    }

    override fun onStart() {
        super.onStart()
        presenter.subscribe(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun displayImages(bitmaps: ArrayList<Bitmap>) {
        viewManager = GridLayoutManager(this, 2)
        viewAdapter = MyAdapter(bitmaps)

        recyclerView = findViewById<RecyclerView>(R.id.mRecyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
            Toast.makeText(applicationContext, MESSAGE_ALL_GREEN, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPermission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager
                        .PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE)
        }else
            presenter.useImages(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_REQUEST_CODE ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    presenter.useImages(this)
                else
                    Toast.makeText(this, MESSAGE_HOUSTON_WHE_HAVE_A_PROBLEM, Toast.LENGTH_SHORT).show()
        }
    }
}
