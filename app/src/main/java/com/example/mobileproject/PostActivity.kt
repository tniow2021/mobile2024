package com.example.mobileproject

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobileproject.databinding.ActivityPostBinding
import com.example.mobileproject.Post

class PostActivity : AppCompatActivity() {

    private lateinit var postTitle: TextView
    private lateinit var postContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postTitle = binding.postTitle
        postContent = binding.postContent

        // 인텐트를 통해 받은 데이터 표시
        val title = intent.getStringExtra("postTitle")
        val content = intent.getStringExtra("postContent")

        postTitle.text = title
        postContent.text = content

       Log.d("dfdf","불러오기에 실패함3.")
        val documents=
            FireStoreConnection.getDocuments("posts")

        if(documents == null)Log.d("dfdf","불러오기에 실패함2.")
        for(document in documents!!){
            var post= document.toObject(Post::class.java)
            if(post!=null){
                postTitle.text = post.title
                postContent.text=post.author
            }
            else{
                Log.d("dfdf","불러오기에 실패함.")
            }

        }
    }

}