package com.androiddev.retrofit_mvvm.util

import com.androiddev.retrofit_mvvm.data.Post

sealed class ApiState {
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data:List<Post>) :ApiState()
    object Empty : ApiState()
}