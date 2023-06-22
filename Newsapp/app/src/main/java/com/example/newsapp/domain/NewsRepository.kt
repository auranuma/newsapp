package com.example.newsapp.domain

import com.example.newsapp.data.remote.model.News

interface NewsRepository {
    suspend fun getNews(): News?
    suspend fun getNewsSearch(searchQuery: String): News?
}