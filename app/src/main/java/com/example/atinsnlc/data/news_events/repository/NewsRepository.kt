package com.example.atinsnlc.data.news_events.repository

import android.util.Log
import com.example.atinsnlc.data.news_events.NewsApi
import com.example.atinsnlc.data.news_events.NewsItem
import com.example.atinsnlc.data.news_events.room.NewsDatabase
import com.example.atinsnlc.data.news_events.room.NewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi, private val newsDatabase: NewsDatabase) {

    suspend fun getNews() {
        Log.d("Repo","get news working...")
        val result = newsApi.getNews()
        if (result.isSuccessful) {
            result.body()?.forEach {
                saveData(it)
            }
        }
    }


    private suspend fun saveData(newsItem: NewsItem) {
        newsDatabase.newsDao().insertOrUpdateNews(NewsEntity(news = newsItem.news, date = newsItem.date))
    }

    fun getData() :Flow<List<NewsEntity>>{
        return newsDatabase.newsDao().getAllNews()
    }

}