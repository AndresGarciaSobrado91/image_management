package com.garcia.image_management.presentation.photo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
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
            ImageProvider.Coil -> holder.binding.ivPhoto.load(photo.url)
            ImageProvider.Glide -> with(holder.binding){
                Glide.with(root)
                    .load("${photo.url}.png")
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPhoto)
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