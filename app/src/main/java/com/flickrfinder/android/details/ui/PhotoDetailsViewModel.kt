package com.flickrfinder.android.details.ui

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.flickrfinder.android.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoDetailsViewModel @Inject constructor(private val imageLoader: ImageLoader) : ViewModel() {

    fun showFullImage(url: String, context: Context, imageView: ImageView) {
        imageLoader.loadImage(url, context, imageView)
    }
}