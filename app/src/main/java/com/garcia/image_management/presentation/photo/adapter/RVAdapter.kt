package com.garcia.image_management.presentation.photo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.garcia.image_management.databinding.ItemPhotoBinding
import com.garcia.image_management.domain.model.Photo

class RVAdapter(val data: List<Photo>,private val imageProvider: ImageProvider, val callback: (Int) -> Unit): RecyclerView.Adapter<RVAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val photo = data[position]
        when(imageProvider){
            ImageProvider.Coil -> {
                val imageLoader =  holder.itemView.context.imageLoader

                // Mix jpg and svg in the recyclerView
                val imageUrl = when(position % 2){
                    0 -> photo.url
                    else -> "https://getapp-test.astropaycard.com/img/crypto/BTC_TEST.svg"
                }

                val request = ImageRequest.Builder(holder.itemView.context)
                    .data(imageUrl)
                    .crossfade(true)
                    .target(holder.binding.ivPhoto)
                    .build()
                imageLoader.enqueue(request)
            }
            ImageProvider.Glide -> with(holder){
                Glide.with(itemView.context)
                    .load("${photo.url}.png")
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(binding.ivPhoto)
            }
        }
        holder.binding.root.setOnClickListener { callback(photo.id) }
        holder.binding.textView.text = "ImageId: ${photo.id}"
    }

    override fun getItemCount(): Int = data.size

    sealed class ImageProvider {
        object Glide : ImageProvider()
        object Coil : ImageProvider()
    }
}