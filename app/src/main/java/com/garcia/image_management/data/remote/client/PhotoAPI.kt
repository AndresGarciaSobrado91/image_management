package com.garcia.image_management.data.remote.client

import com.garcia.image_management.data.remote.dto.PhotoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoAPI {

    @GET("photos")
    suspend fun getPhotos(): List<PhotoDto>

    @GET("photos/{id}")
    suspend fun getPhoto(@Path("id") id: Int): PhotoDto
}