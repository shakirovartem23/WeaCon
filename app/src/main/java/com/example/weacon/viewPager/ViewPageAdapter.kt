package com.example.weacon.viewPager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.weacon.R

class ViewPageAdapter(val drawables: List<Int>): RecyclerView.Adapter<PagerVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.viewpager, parent, false))


    override fun getItemCount(): Int = drawables.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        holder.imageView.setImageResource(drawables[position])
    }
}

class PagerVH(itemView: View): RecyclerView.ViewHolder(itemView){
    var imageView = itemView.findViewById<ImageView>(R.id.imageView)
}