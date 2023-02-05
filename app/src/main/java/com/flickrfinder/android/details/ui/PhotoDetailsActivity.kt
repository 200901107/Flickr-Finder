package com.flickrfinder.android.details.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.flickrfinder.android.AppConstants
import com.flickrfinder.android.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailsActivity : AppCompatActivity() {

    private val photoDetailsViewModel: PhotoDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_details_activity)
        val url = intent.extras?.getString(AppConstants.EXTRAS_KEY_URL)
        val imageView = findViewById<ImageView>(R.id.fullPhoto)
        url?.let {
            photoDetailsViewModel.showFullImage(it, this, imageView)
        }
        findViewById<ImageView>(R.id.back).setOnClickListener {
            finish()
        }
    }
}