package com.example.atinsnlc.data.news_events.room

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}


@Entity(tableName = "news")
data class NewsEntity(
    val news: String,
    val date: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1
)
