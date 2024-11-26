package com.example.mobileproject.boardAndPost

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileproject.FireStoreConnection
import com.example.mobileproject.R
import com.example.mobileproject.databinding.ActivityBoardBinding


class BoardActivity : AppCompatActivity() {

    private var boardPath:String?=""
    private var boardName:String?=""
    private val postList= ArrayList<PostListItem>()
    private lateinit var adapter:PostListAdapter
    private fun refresh()
    {
        FireStoreConnection.onGetCollection(boardPath + "/reference/postList") { documents ->
            for (document in documents) {

                val postListItem = document.toObject(PostListItem::class.java)
                if (postListItem != null)
                    postList.add(postListItem)
            }
            adapter.notifyDataSetChanged()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //activity_board xml파일 바인딩
        val title=binding.boardTitle
        val searchButton=binding.boardSearchButton
        val searchEditText=binding.boardSearchEditText
        val writePostButton=binding.boardWritePostButton
        val listView = binding.boardListView

        //현재보드경로저장
        val intent = intent
        boardPath=intent.getStringExtra("boardPath")
        boardName=intent.getStringExtra("boardName")

        //보드이름을 화면에 띄우기
        title.text=boardName+" 게시판"
        //어댑터 만들기
        adapter = PostListAdapter(this, R.layout.item_post_preview, postList)
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
        //현재 보드의 모든 게시글 정보가져오기
        //모든 게시글 정보를 가져오는데 성공하면 (콜백)
        //나중에 정렬과 갯수제한을 적용해 정보를 받아오도록하자.
        refresh()

        //게시글 쓰기버튼
        writePostButton.setOnClickListener{
            var myintent= Intent(applicationContext, CreatePostActivity::class.java)
            myintent.putExtra("boardPath","boards/boardInfo/board2")
            myintent.putExtra("boardName","board2")
            startActivity(myintent)
        }

        //게시글 검색버튼
        searchButton.setOnClickListener{
            var searchTxt=searchEditText.text.toString()
            if(searchTxt==""){
                //searchEditText에 입력된 값이 없으면 게시판 새로고침하고 토스트띄우기
                refresh()
                Toast.makeText(this,"검색어를 입력해주십시요",Toast.LENGTH_SHORT).show()
            }
            else//검색
            {
                FireStoreConnection.selectDocuments(boardPath!!,
                    "content",searchTxt)
                {
                    success, documents ->
                    if(success){//검색해서 문서들이 받아와졌으면

                        postList.clear()
                        for (document in documents!!) {
                            val post = document.toObject(Post::class.java)
                            if (post != null){
                                //boardPath로 불러온 문서는 Post클래스이기 때문에 리스트뷰에 출력하기위해
                                //PostListItem으로 변환해주는 귀찮은 일을 해줌.
                                val postlistItem=PostListItem.getPostListItem(post!!,document.reference.path.toString(),post!!.timestamp)
                                postList.add(postlistItem)
                            }
                        }
                        //검색결과가 1개이상 있을 때만 리스트뷰를 새로고침
                        if(postList.count()>0){
                            adapter.notifyDataSetChanged()
                        }
                        else{
                            Toast.makeText(this,"검색된 내용이 없습니다.",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(this,"검색된 내용이 없습니다.",Toast.LENGTH_SHORT).show()
                    }
                }
            }

            
        }

    }
}