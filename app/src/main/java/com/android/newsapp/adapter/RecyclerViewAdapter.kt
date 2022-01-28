package com.android.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.newsapp.databinding.RecyclerRowBinding
import com.android.newsapp.model.Article
import com.android.newsapp.view.NewsFragmentDirections
import com.bumptech.glide.Glide

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    var article = mutableListOf<Article>()

    fun setNewsList(article: List<Article>) {
        this.article = article.toMutableList()
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val article = article[position]
        holder.binding.baslik.text = article.title
        Glide.with(holder.itemView.context).load(article.urlToImage).into(holder.binding.gorsel)

        holder.itemView.setOnClickListener {
            val action = NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(position)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return article.size
    }
}