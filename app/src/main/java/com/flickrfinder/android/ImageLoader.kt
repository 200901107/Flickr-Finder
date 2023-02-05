package com.flickrfinder.android

import android.content.Context
import android.widget.ImageView

interface ImageLoader {
    fun loadImage(url: String, context: Context, imageView: ImageView)
}