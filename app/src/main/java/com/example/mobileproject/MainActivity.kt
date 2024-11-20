package com.example.mobileproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileproject.databinding.ActivityMainBinding
/*
project structure에서 안드로이드 API를 35로 설정
뷰바인딩 설정
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //메인화면에 있는 '게시판 가기' 버튼
        binding.goBoardBtn.setOnClickListener{
            var myintent= Intent(applicationContext,BoardActivity::class.java)
            myintent.putExtra("boardPath","boards/boardInfo/board1")
            startActivity(myintent)
        }
        //메인화면에 있는 '게시글 쓰기' 버튼
        binding.createPost.setOnClickListener{
            var myintent= Intent(applicationContext,CreatePost::class.java)
            myintent.putExtra("boardPath","boards/boardInfo/board1")
            startActivity(myintent)
        }


    }
}