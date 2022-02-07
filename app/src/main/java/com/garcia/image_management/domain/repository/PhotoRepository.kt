package com.garcia.image_management.domain.repository

import com.garcia.image_management.common.ResultWrapper
import com.garcia.image_management.domain.model.Photo

interface PhotoRepository {

    suspend fun getPhotos() : ResultWrapper<List<Photo>>

    suspend fun getPhotoById(id: Int) : ResultWrapper<Photo>

}