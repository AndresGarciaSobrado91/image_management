package com.garcia.image_management.presentation.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garcia.image_management.R
import com.garcia.image_management.common.Error
import com.garcia.image_management.common.ResultWrapper
import com.garcia.image_management.domain.model.Photo
import com.garcia.image_management.domain.use_case.photo.GetPhotosUseCase
import com.garcia.image_management.presentation.photo.adapter.RVAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
): ViewModel() {

    var photoList: List<Photo> = emptyList()
        private set

    var currentImageProvider : RVAdapter.ImageProvider = RVAdapter.ImageProvider.Glide
        private set

    data class ViewState(
        val isLoading: Boolean = false,
        val photos: List<Photo>? = null,
        val imageProvider: RVAdapter.ImageProvider? = null,
        val error: Error? = null,
    )

    private val stateMutableLiveData = MutableLiveData(ViewState())
    val stateLiveData = stateMutableLiveData as LiveData<ViewState>

    init {
        getPhotos()
    }

    private fun getPhotos(){
        stateMutableLiveData.value = ViewState(isLoading = true)
        getPhotosUseCase().onEach { result ->
            when(result){
                is ResultWrapper.Error -> {
                    stateMutableLiveData.value = ViewState(error = Error(message = result.message))
                }
                ResultWrapper.NetworkError -> {
                    stateMutableLiveData.value = ViewState(error = Error(resourceId = R.string.connection_error))
                }
                is ResultWrapper.Success -> {
                    photoList = result.value ?: emptyList()
                    stateMutableLiveData.value = ViewState(photos = photoList)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun toggleImageProvider(){
        currentImageProvider = when(currentImageProvider){
            is RVAdapter.ImageProvider.Glide -> RVAdapter.ImageProvider.Coil
            is RVAdapter.ImageProvider.Coil -> RVAdapter.ImageProvider.Glide
        }
        stateMutableLiveData.value = ViewState(imageProvider = currentImageProvider)
    }
}
