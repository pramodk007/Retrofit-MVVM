package com.androiddev.retrofit_mvvm.network

import com.androiddev.retrofit_mvvm.data.Post
import javax.inject.Inject

class PostServiceApiImplementation @Inject constructor(private val postServiceApi: PostServiceApi) {

    suspend fun getPost():List<Post> = postServiceApi.getPost()

}