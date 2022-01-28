package com.android.newsapp.viewmodel

import com.android.newsapp.model.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("top-headlines?country=tr&apiKey=55162e97dd954e3db8b8e8eabd4070bd")
    fun getNews(): Call<News>

    companion object{
        var retrofitService: RetrofitService?= null

        fun getInstance(): RetrofitService {
            if(retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}