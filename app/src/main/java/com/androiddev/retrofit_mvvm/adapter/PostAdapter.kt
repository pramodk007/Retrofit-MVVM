package com.androiddev.retrofit_mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androiddev.retrofit_mvvm.data.Post
import com.androiddev.retrofit_mvvm.databinding.PostSingleRowBinding
import com.bumptech.glide.Glide

class PostAdapter(private var postList:List<Post>): ListAdapter<Post, PostAdapter.PostViewHolder>(PostComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostSingleRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class PostViewHolder(private val binding: PostSingleRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Post) {
            binding.apply {
                Glide.with(itemView)
                    .load(result.url)
                    .into(imgPic)
                textViewHead.text = result.title
                textViewDesc.text = result.albumId.toString()
            }
        }
    }

    class PostComparator : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Post, newItem: Post) =
            oldItem == newItem

    }

}