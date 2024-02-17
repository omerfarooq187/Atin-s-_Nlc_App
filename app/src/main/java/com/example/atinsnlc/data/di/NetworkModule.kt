package com.example.atinsnlc.data.di

import com.example.atinsnlc.data.news_events.NewsApi
import com.example.atinsnlc.data.registration.RegistrationApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://umarfarooq90.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .build()
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRegistrationApi(retrofit: Retrofit): RegistrationApi {
        return retrofit.create(RegistrationApi::class.java)
    }
}