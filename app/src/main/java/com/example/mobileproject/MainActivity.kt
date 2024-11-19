package com.example.mobileproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobileproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //메인화면에 있는 '게시판 가기' 버튼
        binding.goBoardBtn.setOnClickListener{
            Log.d("fffe","EFfe");
            var myintent= Intent(applicationContext,Board::class.java)
            startActivity(myintent)
        }
        //메인화면에 있는 '게시글 쓰기' 버튼
        binding.createPost.setOnClickListener{
            Log.d("fffe","EFf3443e");
            var myintent= Intent(applicationContext,CreatePost::class.java)
            startActivity(myintent)
        }


    }
}