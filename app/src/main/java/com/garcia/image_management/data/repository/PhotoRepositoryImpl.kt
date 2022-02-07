package com.garcia.image_management.data.repository

import com.garcia.image_management.common.ResultWrapper
import com.garcia.image_management.data.remote.client.PhotoAPI
import com.garcia.image_management.data.remote.network.ResponseHandler
import com.garcia.image_management.domain.model.Photo
import com.garcia.image_management.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val api: PhotoAPI,
    private val responseHandler: ResponseHandler,
): PhotoRepository {

    override suspend fun getPhotos(): ResultWrapper<List<Photo>> {
        return responseHandler {
            api.getPhotos().map { photoDto -> photoDto.toPhoto() }
        }
    }

    override suspend fun getPhotoById(id: Int): ResultWrapper<Photo> {
        return responseHandler {
            api.getPhoto(id).toPhoto()
        }
    }
}