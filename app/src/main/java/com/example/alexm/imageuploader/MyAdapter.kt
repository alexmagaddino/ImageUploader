package com.example.alexm.imageuploader

import android.graphics.Bitmap
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView

class MyAdapter(private val myDataset: ArrayList<Bitmap>) :RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.ViewHolder {
        // create a new view
        val imageView = LayoutInflater.from(parent.context)
                .inflate(R.layout.image_view, parent, false) as ImageView

        return ViewHolder(imageView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.imageView.setImageBitmap(myDataset.get(position))
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}