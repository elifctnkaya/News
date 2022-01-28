package com.android.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.newsapp.model.Article
import com.android.newsapp.model.News
import com.android.newsapp.viewmodel.NewsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel constructor(private val repository: NewsRepository): ViewModel() {

    val articleList = MutableLiveData<List<Article>>()
    val newsList = MutableLiveData<News>()
    val errorMessage = MutableLiveData<String>()

    fun getAllNews(){
        val response = repository.getNews()
        response.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                newsList.postValue(response.body())
                articleList.postValue(response.body()?.articles)
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}