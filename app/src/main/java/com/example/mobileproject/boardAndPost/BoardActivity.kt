package com.example.mobileproject.boardAndPost

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileproject.FireStoreConnection
import com.example.mobileproject.R
import com.example.mobileproject.databinding.ActivityBoardBinding

class BoardActivity : AppCompatActivity() {

    private var boardPath:String?=""
    private var boardName:String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //activity_board xml파일 바인딩
        val title=binding.boardTitle
        val searchEditText=binding.boardSearchEditText
        val writePostButton=binding.boardWritePostButton
        val listView = binding.boardListView

        //현재보드경로저장
        val intent = intent
        boardPath=intent.getStringExtra("boardPath")
        boardName=intent.getStringExtra("boardName")

        //보드이름을 화면에 띄우기
        title.text=boardName+" 게시판"


        val postList= ArrayList<PostListItem>()
        //현재 보드의 모든 게시글 정보가져오기
        //모든 게시글 정보를 가져오는데 성공하면 (콜백)
        //나중에 정렬과 갯수제한을 적용해 정보를 받아오도록하자.
        FireStoreConnection.onGetCollection(boardPath + "/reference/postList") { documents ->
            for (document in documents) {

                val postListItem = document.toObject(PostListItem::class.java)
                if (postListItem != null)
                    postList.add(postListItem)
            }
            val adapter = PostListAdapter(this, R.layout.item_post_preview, postList)
            listView.adapter = adapter;//이 줄에서 리스틉 뷰에 게시글 목록이 출력됨.
            // 리스트 아이템 클릭 시 게시글 화면으로 이동
            listView.setOnItemClickListener { _, _, position, _ ->
                // 클릭된 게시글 객체ㄹ
                val selectedPost = postList[position]
                // 게시글 화면으로 이동
                val myintent = Intent(applicationContext, PostActivity::class.java)
                //액티비티간 매개변수는 게시글경로
                myintent.putExtra("postPath", selectedPost.postPath)
                startActivity(myintent)
            }

        }
    }
}