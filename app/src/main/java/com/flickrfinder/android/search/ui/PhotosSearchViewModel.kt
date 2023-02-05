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
import java.lang.Exception
import javax.inject.Inject

sealed class ApiStatus {
    object Loading: ApiStatus()
    data class Success(val data: ArrayList<PhotoData>): ApiStatus()
    data class Failure(val exception: Exception): ApiStatus()
}

@HiltViewModel
class PhotosSearchViewModel @Inject constructor(
    private val photosSearchRepository: PhotosSearchRepository
) : ViewModel() {

    private val _photosList = MutableStateFlow<ApiStatus>(ApiStatus.Loading)
    val photosList = _photosList

    fun searchPublicPhotos(text: String = "") {
        viewModelScope.launch {
            val photosList = getImageList(photosSearchRepository.getPhotos(text))
            _photosList.value = ApiStatus.Success(photosList)
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