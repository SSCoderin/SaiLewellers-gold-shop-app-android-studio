package com.RecentlyAdd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sj.saijewellers.R


import com.squareup.picasso.Picasso

class NewAdapter : RecyclerView.Adapter<NewAdapter.ViewHolder>() {

    private var dataList: List<NewData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_new_data, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newData = dataList[position]
        holder.nameTextView.text = newData.name
        holder.weightTextView.text = newData.weight
        holder.descriptionTextView.text = newData.description

        // Use Picasso or Glide to load the image from the URL
        Picasso.get().load(newData.imageUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(dataList: List<NewData>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val weightTextView: TextView = itemView.findViewById(R.id.weightTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}

