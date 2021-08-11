package com.androiddev.retrofit_mvvm.data

import com.google.gson.annotations.SerializedName

data class Post (

    @SerializedName("albumId") var albumId : Int,
    @SerializedName("id") var id : Int,
    @SerializedName("title") var title : String,
    @SerializedName("url") var url : String,
    @SerializedName("thumbnailUrl") var thumbnailUrl : String

)