package com.flickrfinder.android.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flickr4java.flickr.photos.Photo
import com.flickr4java.flickr.photos.PhotoList
import com.flickrfinder.android.data.PhotoData
import com.flickrfinder.android.search.data.PhotosSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosSearchViewModel @Inject constructor(
    private val photosSearchRepository: PhotosSearchRepository
) : ViewModel() {

    private val _photosList = MutableStateFlow<ArrayList<PhotoData>>(arrayListOf())
    val photosList = _photosList

    fun searchPublicPhotos(text: String = "") {
        viewModelScope.launch {
            val photosList = getImageList(photosSearchRepository.getPhotos(text))
            _photosList.value = photosList
        }
    }

    fun getImageList(list: PhotoList<Photo>): ArrayList<PhotoData> {
        val photoList = arrayListOf<PhotoData>()
        for (photo in list) {
            photoList.add(PhotoData(photo.thumbnailUrl, photo.largeUrl , photo.title))
        }
        return photoList
    }

}