package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): News

    @GET("everything")
    suspend fun getNewsSearch(
        @Query("q") keyword: String,
        @Query("language") language: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): News
}