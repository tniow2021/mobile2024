package com.example.mobileproject.boardAndPost

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mobileproject.FireStorageConnection
import com.example.mobileproject.FireStoreConnection
import com.example.mobileproject.databinding.ActivityPostBinding
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostActivity : AppCompatActivity() {

    private lateinit var postTitle: TextView
    private lateinit var postDate: TextView
    private lateinit var postAuther: TextView
    private lateinit var postContent: TextView
    private lateinit var postImage: ImageView

    companion object{

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("sex","hhhhhhhhhhhhhhhhhhhhhhhh");
        postTitle = binding.postTitle
        postDate = binding.postDate
        postAuther = binding.postAuther
        postContent = binding.postContent
        postImage = binding.postImageView

        // 인텐트를 통해 받은 데이터 표시
        val postPath = intent.getStringExtra("postPath")
        // 받은 게시글 경로가 null이면 종료.
        if(postPath == null){
            Log.d("dfdf","불러오기에 실패함5.")
            finish()
        }
        //게시물경로로 게시물(post객체)받아오기
        FireStoreConnection.onGetDocument(postPath!!) { document ->
            val post: Post? = document.toObject(Post::class.java)

            if (post == null)//게시글을 받아와도 안에든게 없으면 종료.
            {
                Log.d("dfdf", "불러오기에 실패함t=8.")
                finish()
            }
            //post 객체안의 데이터를 뺴내어 화면에 표시하기.
            postTitle.text = post?.title ?: ""
            postDate.text = post?.title ?: ""
            postAuther.text = post?.author ?: ""
            postContent.text = post?.content ?: ""
            // postListItem안에 있는 Timestamp를 date로 변환해 표기하기
            val date = Date(post?.timestamp ?: 0)
            // 날짜 포맷 설정
            //val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss", Locale.getDefault())
            val formattedDate = dateFormat.format(date)
            postDate.text = formattedDate


            // 이미지
            /*
            if (post?.imagePath != null) {
                // Firebase Storage 참조
                val storageReference = FirebaseStorage.getInstance().reference

                // 이미지 경로 설정 imagePath는 파이어스토리지상의 경로
                val imageRef = storageReference.child(post.imagePath!!)

                // 이미지의 다운로드 URL 가져오기
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    // Glide를 사용하여 ImageView에 이미지 로드
                    Glide.with(this)
                        .load(uri)  // Firebase에서 받은 다운로드 URL 사용
                        .into(postImage)  // ImageView에 이미지 설정
                }.addOnFailureListener {
                    // 이미지 로딩 실패 시 처리
                }
            } else {
                //이미지가 없으면 이미지뷰를 안보이게한다.
                postImage.visibility = ImageView.INVISIBLE
            }
             */
            if(post?.imagePath != null)
            {
                FireStorageConnection.bindImageByPath(this,post.imagePath!!,postImage)
            }

        }


       Log.d("dfdf","불러오기에 실패함3.")
    }

}