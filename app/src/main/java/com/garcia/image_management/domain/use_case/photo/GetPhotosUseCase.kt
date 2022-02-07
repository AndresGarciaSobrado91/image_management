package com.garcia.image_management.domain.use_case.photo

import com.garcia.image_management.common.ResultWrapper
import com.garcia.image_management.domain.model.Photo
import com.garcia.image_management.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) {

    operator fun invoke(): Flow<ResultWrapper<List<Photo>>> = flow {
        emit(repository.getPhotos())
    }
}