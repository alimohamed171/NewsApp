package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.models.Article

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {


    inner class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        private val articleImage: ImageView = itemView.findViewById(R.id.articleImage)
        private val articleSource: TextView = itemView.findViewById(R.id.articleSource)
        private val articleTitle: TextView = itemView.findViewById(R.id.articleTitle)
        private val articleDescription: TextView = itemView.findViewById(R.id.articleDescription)
        private val articleDateTime: TextView = itemView.findViewById(R.id.articleDateTime)


        fun bind(article: Article) {
            Glide.with(itemView).load(article.urlToImage).into(articleImage)
            articleSource.text = article.source.name
            articleTitle.text = article.title
            articleDescription.text = article.description
            articleDateTime.text = article.publishedAt

            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }





    private val diffCallback = object :DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this ,diffCallback)
    private var onItemClickListener: ((Article) -> Unit)? =null

    fun setOnItemClickListener(listener: (Article)->Unit){
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news,parent,false)
        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }


    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        holder.bind(article)

    }







}
