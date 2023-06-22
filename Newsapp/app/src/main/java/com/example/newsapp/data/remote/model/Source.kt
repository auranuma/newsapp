package com.example.newsapp.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    @Expose
    val id: Any,

    @Expose
    @SerializedName("name")
    val name: String
)