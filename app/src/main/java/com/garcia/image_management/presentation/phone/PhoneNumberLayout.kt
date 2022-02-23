package com.garcia.image_management.presentation.phone

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.ImageViewCompat
import coil.imageLoader
import coil.request.ImageRequest
import com.garcia.image_management.databinding.LayoutPhoneNumberBinding

class PhoneNumberLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding = LayoutPhoneNumberBinding.inflate(LayoutInflater.from(context), this)

    init {
        setIcon("https://getapp-test.astropaycard.com/img/flags/UY.svg")
    }

    fun setIcon(url: String?) {
        val imageLoader = context.imageLoader
        val request = ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .target(binding.ivPhoneCountryFlag)
            .build()
        imageLoader.enqueue(request)
    }
}