package com.example.mobileproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileproject.boardAndPost.*
import com.example.mobileproject.databinding.ActivityMainBinding
/*
project structure에서 안드로이드 API를 35로 설정
파이어베이스 연동 안되있으면 tools에서 firebase에서 FireStore랑 FireStorage 연동 및 sdk다운받기
뷰바인딩 설정
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //메인화면에 있는 '게시판 가기' 버튼
        binding.goBoardBtn.setOnClickListener{
            var myintent= Intent(applicationContext, BoardActivity::class.java)
            myintent.putExtra("boardPath","boards/boardInfo/board2")
            myintent.putExtra("boardName","board2")
            startActivity(myintent)
        }
        //메인화면에 있는 '게시글 쓰기' 버튼
        binding.createPost.setOnClickListener{
            var myintent= Intent(applicationContext, CreatePostActivity::class.java)
            myintent.putExtra("boardPath","boards/boardInfo/board2")
            myintent.putExtra("boardName","board2")
            startActivity(myintent)
        }


    }
}