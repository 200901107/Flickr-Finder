package com.flickrfinder.android.search.data

import com.flickr4java.flickr.photos.Photo
import com.flickr4java.flickr.photos.PhotoList

interface PhotosSearchRepository {
    suspend fun getPhotos(text: String): PhotoList<Photo>
}