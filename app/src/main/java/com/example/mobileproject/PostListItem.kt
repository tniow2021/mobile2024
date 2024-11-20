package com.example.mobileproject

import java.util.Date

data class PostListItem(
    var title: String="",
    var author: String="",
    var postPath:String="",
    var timestamp: Long=0
){
    companion object{
        fun getPostListItem(post: Post,postPath:String):PostListItem
        {
            return PostListItem(post.title,post.author, postPath,Date().getTime())
        }
    }
}