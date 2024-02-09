package com.example.newsapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: MainActivity) : RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        val viewholder =NewsViewHolder(view)
        view.setOnClickListener{
            listener.onNewsClick(items[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder:NewsViewHolder, position: Int) {
         val currentItem=items[position]
        holder.news.text = currentItem.news
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.thumbnail)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedNews: ArrayList<News>) {
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }

}

class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val thumbnail:ImageView= itemView.findViewById(R.id.thumbnail)
    val news:TextView=itemView.findViewById(R.id.news)
    val author:TextView=itemView.findViewById(R.id.author)
}

interface NewsClicked{
    fun onNewsClick(news:News)
}