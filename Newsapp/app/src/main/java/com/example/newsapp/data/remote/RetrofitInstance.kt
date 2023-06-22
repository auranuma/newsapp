package com.example.newsapp.data.remote

import com.example.newsapp.core.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private fun retrofitService(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build()
    }

    val api: ApiService by lazy {
        retrofitService().create(ApiService::class.java)
    }
}