package com.example.atinsnlc.data.news_events.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Upsert
    suspend fun insertOrUpdateNews(newsEntity: NewsEntity)

    @Query("SELECT * FROM news")
    fun getAllNews(): Flow<List<NewsEntity>>
}