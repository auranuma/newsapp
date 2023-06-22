package com.example.newsapp.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article(
    @Expose
    @SerializedName("source")
    val source: Source,

    @Expose
    @SerializedName("author")
    val author: String,

    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("description")
    val description: String,

    @Expose
    @SerializedName("url")
    val url: String,

    @Expose
    @SerializedName("urlToImage")
    val urlToImage: String,

    @Expose
    @SerializedName("publishedAt")
    val publishedAt: String
) : Serializable {
    override fun hashCode(): Int {
        return super.hashCode()
    }
}