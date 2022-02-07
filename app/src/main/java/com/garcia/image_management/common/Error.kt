package com.garcia.image_management.common

import com.garcia.image_management.R

data class Error(
    val message: String? = null,
    val resourceId: Int = R.string.generic_error,
)