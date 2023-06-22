package com.example.newsapp.ui.news

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.newsapp.R
import com.example.newsapp.core.Utils
import com.example.newsapp.data.remote.model.Article
import com.example.newsapp.ui.news.NewsAdapter.NewsViewHolder

class NewsAdapter(private val articleList: List<Article>) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articleList[position]
        holder.title.text = article.title
        holder.desc.text = article.description
        holder.author.text = article.author
        holder.time.text = " \u2022" + Utils.dateToTimeFormat(article.publishedAt)
        holder.publishedAt.text = Utils.dateFormat(article.publishedAt)
        holder.source.text = article.source.name
        val imageUrl = article.urlToImage
        Glide.with(holder.imageView).load(imageUrl).addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                holder.progressBar.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                holder.progressBar.visibility = View.GONE
                return false
            }

        }).into(holder.imageView)

        holder.cardView.setOnClickListener { v ->
            findNavController(v).navigate(
                NewsFragmentDirections.actionNewsFragmentToDetailsFragment(
                    article
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var desc: TextView
        var author: TextView
        var publishedAt: TextView
        var source: TextView
        var time: TextView
        var imageView: ImageView
        var progressBar: ProgressBar
        var cardView: CardView

        init {
            title = itemView.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.desc)
            author = itemView.findViewById(R.id.author)
            time = itemView.findViewById(R.id.time)
            publishedAt = itemView.findViewById(R.id.publishedAt)
            source = itemView.findViewById(R.id.source)
            cardView = itemView.findViewById(R.id.cardView)
            imageView = itemView.findViewById(R.id.image)
            progressBar = itemView.findViewById(R.id.progress_bar)
        }
    }
}