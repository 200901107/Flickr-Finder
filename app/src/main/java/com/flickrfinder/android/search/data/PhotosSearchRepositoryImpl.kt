package com.flickrfinder.android.search.data

import com.flickr4java.flickr.photos.Photo
import com.flickr4java.flickr.photos.PhotoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotosSearchRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    PhotosSearchRepository {

    override suspend fun getPhotos(text: String): PhotoList<Photo> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.getPhotos(text)
        }
    }

}