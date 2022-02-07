package com.garcia.image_management.data.remote.dto


import com.garcia.image_management.domain.model.Photo
import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("albumId")
    val albumId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
){
    fun toPhoto(): Photo{
        return Photo(
            id = id,
            thumbnailUrl = thumbnailUrl,
            title = title,
            url = url
        )
    }
}