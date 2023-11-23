package com.example.hw_3_month5


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.hw_3_month5.databinding.ItemImageBinding



class ImageAdapter(var list: MutableList<ImageModel>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    fun addData(newItem: ImageModel) {
        list.add(newItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ImageViewHolder(val binding: ItemImageBinding) : ViewHolder(binding.root) {
        fun bind(model: ImageModel) {
            binding.imageView.load(model.largeImageURL)
        }
    }
}