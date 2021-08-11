package com.androiddev.retrofit_mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddev.retrofit_mvvm.R
import com.androiddev.retrofit_mvvm.adapter.PostAdapter
import com.androiddev.retrofit_mvvm.databinding.ActivityMainBinding
import com.androiddev.retrofit_mvvm.util.ApiState
import com.androiddev.retrofit_mvvm.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        viewModel.getPost()
        lifecycleScope.launchWhenStarted {
            viewModel._postStateFlow.collect {
                when(it){
                    is ApiState.Loading -> {
                        binding.rvSongsList.isVisible = false
                        binding.progressBar.isVisible = true
                    }
                    is ApiState.Failure -> {
                        binding.rvSongsList.isVisible = false
                        binding.progressBar.isVisible = false
                        Log.d("main","onCreate:${it.msg}")
                    }
                    is ApiState.Success ->{
                        binding.rvSongsList.isVisible = true
                        binding.progressBar.isVisible = false
                        postAdapter.submitList(it.data)
                    }
                }
            }
        }

    }

    private fun initRecyclerView() {
        postAdapter = PostAdapter(ArrayList())
        binding.rvSongsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }
}