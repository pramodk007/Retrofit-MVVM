package com.androiddev.retrofit_mvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddev.retrofit_mvvm.repository.PostRepository
import com.androiddev.retrofit_mvvm.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private  val postRepository: PostRepository):ViewModel() {

    private val postStateFlow:MutableStateFlow<ApiState> =  MutableStateFlow(ApiState.Empty)
    val _postStateFlow:StateFlow<ApiState> = postStateFlow

    fun getPost() = viewModelScope.launch {
        postStateFlow.value = ApiState.Loading
        postRepository.getPosts()
            .catch { e->
                postStateFlow.value =ApiState.Failure(e)
            }
            .collect { data->
                postStateFlow.value = ApiState.Success(data)
            }
    }
}