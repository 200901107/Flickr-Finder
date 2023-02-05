package com.flickrfinder.android.search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.flickrfinder.android.ImageLoader
import com.flickrfinder.android.R
import com.flickrfinder.android.data.PhotoData

class PhotosAdapter (
    private var photoList: ArrayList<PhotoData>,
    private val imageLoader: ImageLoader,
    private val itemClickListener: OnClickListener
) : RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    fun setList(list: ArrayList<PhotoData>) {
        photoList = list
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photoView: ImageView = itemView.findViewById(R.id.photo_item)
        private val title: TextView = itemView.findViewById(R.id.photo_title)

        fun bind(position: Int) {
            val photo = photoList[position]
            imageLoader.loadImage(photo.thumbnailUrl, itemView.context, photoView)
            title.text = photo.title
            itemView.setOnClickListener {
                itemClickListener.onClick(photo.thumbnailUrl)
            }
        }
    }

    class OnClickListener(val clickListener: (fullUrl: String) -> Unit) {
        fun onClick(fullPhotoUrl: String) = clickListener(fullPhotoUrl)
    }

}
