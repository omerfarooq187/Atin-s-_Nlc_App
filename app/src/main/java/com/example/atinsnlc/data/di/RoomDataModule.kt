package com.example.atinsnlc.data.di

import android.content.Context
import androidx.room.Room
import com.example.atinsnlc.data.news_events.room.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomDataModule {
    @Singleton
    @Provides
    fun provideNewsData(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_database"
        ).build()
    }
}