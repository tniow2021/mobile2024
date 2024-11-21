package com.example.mobileproject.boardAndPost

import java.util.Date
/*
    게시판(BoardActivity)에 들어가서 게시글 목록이 띄어질때
    리스트뷰에 각 항목의 데이터를 정의하는 클래스임
 */
data class PostListItem(
    var title: String="",
    var author: String="",
    var postPath:String="",
    var previewImagePath:String?="",
    var timestamp: Long=0
){
    companion object{
        fun getPostListItem(post: Post, postPath:String): PostListItem
        {
            //Post를 받은뒤 Post내의 표면적인 정보(제목,지은이,이미지경로)와 현재시간정보를 집어넣어 반환
            return PostListItem(post.title,post.author, postPath,post.imagePath,Date().getTime())
            //여기서는 프리뷰이미지로 post안에 있는 이미지를 넣었는데 나중에 이미지를 여러개 넣을 수 만들게되면
            //첫번째 이미지를 프리뷰이미지로 지정하도록하면 됨.
        }
    }
}