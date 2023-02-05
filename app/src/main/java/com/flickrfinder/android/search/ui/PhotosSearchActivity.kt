package com.flickrfinder.android.search.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flickrfinder.android.AppConstants.Companion.EXTRAS_KEY_URL
import com.flickrfinder.android.ImageLoader
import com.flickrfinder.android.R
import com.flickrfinder.android.details.ui.PhotoDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class PhotosSearchActivity : AppCompatActivity() {

    private val photosSearchViewModel: PhotosSearchViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photos_search_activity)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val recyclerViewManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = recyclerViewManager
        val photosAdapter = PhotosAdapter(arrayListOf(), imageLoader, PhotosAdapter.OnClickListener {
            onPhotoClick(it)
        })
        recyclerView.adapter = photosAdapter

        val searchBar = findViewById<EditText>(R.id.search_bar)
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                photosSearchViewModel.searchPublicPhotos(p0.toString())
            }
        })

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                photosSearchViewModel.photosList.collect {
                    photosAdapter.setList(it)
                }
            }
        }

        photosSearchViewModel.searchPublicPhotos()
    }

    fun onPhotoClick(fullImageUrl: String) {
        val intent = Intent(this, PhotoDetailsActivity::class.java)
        intent.putExtra(EXTRAS_KEY_URL, fullImageUrl)
        startActivity(intent)
    }
}