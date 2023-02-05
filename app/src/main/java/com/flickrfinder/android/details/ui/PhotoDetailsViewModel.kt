package com.flickrfinder.android.details.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

sealed class UiState {
    object NoState: UiState()
    object ClickedBackButton: UiState()
    data class LoadPhoto(val url: String): UiState()
}

class PhotoDetailsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.NoState)
    val uiState = _uiState

    fun showFullImage(url: String) {
        _uiState.value = UiState.LoadPhoto(url)
    }

    fun onBackButtonClicked() {
        _uiState.value = UiState.ClickedBackButton
    }
}