package com.androiddev.retrofit_mvvm.network

import com.androiddev.retrofit_mvvm.data.Post
import retrofit2.http.GET

interface PostServiceApi {

    @GET("posts")
    suspend fun getPost():List<Post>
}