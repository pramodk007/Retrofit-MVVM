package com.androiddev.retrofit_mvvm.repository

import com.androiddev.retrofit_mvvm.data.Post
import com.androiddev.retrofit_mvvm.network.PostServiceApiImplementation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepository @Inject constructor(private val postServiceApiImplementation: PostServiceApiImplementation){

    fun getPosts():Flow<List<Post>> = flow {
        emit(postServiceApiImplementation.getPost())
    }.flowOn(Dispatchers.IO)
}