package com.example.newsapp.repository

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.models.Article
import java.util.Locale.IsoCountryCode

class NewsRepository(val db: ArticleDatabase) {


    suspend fun getHeadlines(countryCode: String , pageNumber: Int)=
        RetrofitInstance.api.getHeadlines(countryCode, pageNumber)

    suspend fun search(query:String,pageNumber:Int)=
        RetrofitInstance.api.search(query,pageNumber)


    suspend fun insertDB(article: Article)=
        db.getArticleDao().insert(article)

    suspend fun deleteArticleDB(article: Article)=
        db.getArticleDao().deleteArticle(article)
    fun getFavouritesDB()=
        db.getArticleDao().getAllArticles()




}