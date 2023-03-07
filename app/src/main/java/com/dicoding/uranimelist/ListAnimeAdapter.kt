package com.dicoding.uranimelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.uranimelist.databinding.ItemRowAnimeBinding

class ListAnimeAdapter(private val listAnime: ArrayList<Anime>) : RecyclerView.Adapter<ListAnimeAdapter.ListViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(vg: ViewGroup, i: Int): ListViewHolder {
        val binding= ItemRowAnimeBinding.inflate(LayoutInflater.from(vg.context), vg, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title,genre,synopsis,image) = listAnime[position]
        Glide.with(holder.itemView.context)
            .load(image)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvTitle.text = title
        holder.binding.tvGenre.text = genre
        holder.binding.tvDesc.text = synopsis.substring(0,30) + "..."
        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(listAnime[holder.adapterPosition])}
    }

    override fun getItemCount(): Int = listAnime.size

    class ListViewHolder(var binding: ItemRowAnimeBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Anime)
    }
}
