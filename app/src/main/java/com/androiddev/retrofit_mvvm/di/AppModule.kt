package com.androiddev.retrofit_mvvm.di

import com.androiddev.retrofit_mvvm.network.PostServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideUrl():String = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun providePostApiService(url:String):PostServiceApi =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostServiceApi::class.java)

}