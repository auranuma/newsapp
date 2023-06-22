package com.example.newsapp.data

import com.example.newsapp.core.Constant
import com.example.newsapp.data.remote.RetrofitInstance
import com.example.newsapp.data.remote.model.News
import com.example.newsapp.domain.NewsRepository

class NewsRepositoryImpl : NewsRepository {

    private val api = RetrofitInstance.api

    override suspend fun getNews(): News? {
        return try {
            api.getNews(Constant.COUNTRY, Constant.API_KEY)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getNewsSearch(searchQuery: String): News? {
        return try {
            api.getNewsSearch(searchQuery, Constant.COUNTRY, Constant.SORT_BY, Constant.API_KEY)
        } catch (e: Exception) {
            null
        }
    }
}