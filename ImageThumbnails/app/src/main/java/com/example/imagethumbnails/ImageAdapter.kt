package com.example.imagethumbnails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

// Amber Lawson
// GP Gridview Images
// 06/04/2026
internal class ImageAdapter(
    private val context: Context,
    private val imagesList: List<GridViewModal>
) :
    BaseAdapter() {
    override fun getCount(): Int {
        return imagesList.size
    }

    override fun getItem(position: Int): Any? {
        return imagesList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val rowView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.row_item, parent, false)
        val imageView = rowView.findViewById<ImageView>(R.id.imgRowItem)
        val textView = rowView.findViewById<TextView>(R.id.txtRowItem)
        val imageItem = imagesList[position]

        imageView.setImageResource(imageItem.imageId)
        textView.text = imageItem.imageName
        return rowView
    }
}
