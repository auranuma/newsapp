package com.example.newsapp.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.NewsRepositoryImpl
import com.example.newsapp.data.remote.model.News
import com.example.newsapp.domain.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val newsRepository: NewsRepository = NewsRepositoryImpl()

    private val _newsLiveData = MutableLiveData<News?>()
    val newsLiveData: LiveData<News?> get() = _newsLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val news = newsRepository.getNews()
            _newsLiveData.postValue(news)
        }
    }
}