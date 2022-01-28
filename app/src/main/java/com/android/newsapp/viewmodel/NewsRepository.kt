package com.android.newsapp.viewmodel

class NewsRepository constructor(private val retrofitService: RetrofitService) {

    fun getNews() = retrofitService.getNews()
}