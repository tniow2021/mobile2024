package com.example.mobileproject

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileproject.databinding.ActivityBoardBinding
import com.example.mobileproject.databinding.PostListItemBinding

class Board : AppCompatActivity() {
    lateinit var listView:ListView
    lateinit var postList: ArrayList<Post>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listView = findViewById(R.id.listView)

        // 게시글 목록 데이터 생성
        postList = ArrayList()
        postList.add(Post("첫 번째 게시글", "작성자1"))
        postList.add(Post("두 번째 게시글", "작성자2"))
        postList.add(Post("세 번째 게시글", "작성자3"))

        val adapter = PostListAdapter(this, R.layout.post_list_item, postList)
        listView.adapter = adapter
        // 리스트 아이템 클릭 시 게시글 화면으로 이동
        listView.setOnItemClickListener { _, _, position, _ ->
            // 클릭된 게시글 객체
            val selectedPost = postList[position]

            // 게시글 화면으로 이동
            val intent = Intent(this, PostActivity::class.java).apply {
                putExtra("postTitle", selectedPost.title)
                putExtra("postContent", "게시글 내용 예시입니다.")  // 내용은 예시로 넣음
            }
            startActivity(intent)
        }
    }
}