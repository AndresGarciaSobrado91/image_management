package com.garcia.image_management.domain.model

data class Photo(
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)