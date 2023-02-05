package com.flickrfinder.android.details.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.flickrfinder.android.AppConstants
import com.flickrfinder.android.ImageLoader
import com.flickrfinder.android.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PhotoDetailsActivity : AppCompatActivity() {

    private lateinit var fullImageView: ImageView

    @Inject
    lateinit var imageLoader: ImageLoader

    private val photoDetailsViewModel: PhotoDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_details_activity)
        val url = intent.extras?.getString(AppConstants.EXTRAS_KEY_URL)
        fullImageView = findViewById<ImageView>(R.id.fullPhoto)
        findViewById<ImageView>(R.id.back).setOnClickListener {
            photoDetailsViewModel.onBackButtonClicked()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                photoDetailsViewModel.uiState.collect {
                    when (it) {
                        is UiState.NoState -> {
                            // No data state
                        }
                        is UiState.LoadPhoto -> {
                            loadPhoto(it.url)
                        }
                        is UiState.ClickedBackButton -> {
                            finish()
                        }
                    }
                }
            }
        }

        url?.let {
            photoDetailsViewModel.showFullImage(it)
        }
    }

    private fun loadPhoto(url: String) {
        imageLoader.loadImage(url, this, fullImageView)
    }
}