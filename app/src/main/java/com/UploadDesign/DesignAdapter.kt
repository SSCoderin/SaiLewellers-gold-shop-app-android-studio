package com.UploadDesign

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sj.saijewellers.R
import com.squareup.picasso.Picasso

class DesignAdapter(private val context: Context, private val designList: List<Design>) :
    RecyclerView.Adapter<DesignAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewDesign: ImageView = itemView.findViewById(R.id.imageViewDesign)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewContact: TextView = itemView.findViewById(R.id.textViewContact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val design = designList[position]

        Picasso.get().load(design.image).into(holder.imageViewDesign)
        holder.textViewName.text = "Name: ${design.name}"
        holder.textViewContact.text = "Contact: ${design.contact}"
    }

    override fun getItemCount(): Int {
        return designList.size
    }
}
