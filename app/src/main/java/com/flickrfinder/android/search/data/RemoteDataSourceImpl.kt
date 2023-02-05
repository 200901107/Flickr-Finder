package com.flickrfinder.android.search.data

import com.flickr4java.flickr.Flickr
import com.flickr4java.flickr.photos.Photo
import com.flickr4java.flickr.photos.PhotoList
import com.flickr4java.flickr.photos.SearchParameters
import com.flickrfinder.android.search.data.RemoteDataSource
import java.util.stream.Collectors
import java.util.stream.Stream
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val flickr: Flickr
) : RemoteDataSource {
    override suspend fun getPhotos(text: String): PhotoList<Photo> {
        val photosInterface = flickr.photosInterface
        val params = SearchParameters()
        params.media = "photos" // One of "photos", "videos" or "all"
        params.extras = Stream.of("media").collect(Collectors.toSet())
        params.text = text
        return photosInterface.search(params, 25, 0)
    }
}