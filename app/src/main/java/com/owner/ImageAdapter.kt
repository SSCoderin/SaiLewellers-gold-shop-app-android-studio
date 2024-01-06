package com.owner

import androidx.recyclerview.widget.RecyclerView

// ImageAdapter.kt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sj.saijewellers.databinding.ItemImageBinding

class ImageAdapter : ListAdapter<ImageDetails, ImageAdapter.ImageViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageDetails = getItem(position)
        holder.bind(imageDetails)
    }

    class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageDetails: ImageDetails) {
            // Bind data to views
            // (e.g., image loading using Glide or Picasso)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<ImageDetails>() {
        override fun areItemsTheSame(oldItem: ImageDetails, newItem: ImageDetails): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ImageDetails, newItem: ImageDetails): Boolean {
            return oldItem == newItem
        }
    }
}
