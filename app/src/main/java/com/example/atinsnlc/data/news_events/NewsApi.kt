package com.example.atinsnlc.data.news_events

import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET("news")
    suspend fun getNews(): Response<News>
}