package com.flickrfinder.android

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class AppImageLoader : ImageLoader {
    override fun loadImage(url: String, context: Context, imageView: ImageView) {
        Glide
            .with(context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}