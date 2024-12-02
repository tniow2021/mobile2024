package com.example.FootBall.footBall_damyeong.boardAndPost

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.FootBall.FireStorageConnection
import com.example.FootBall.FireStoreConnection
import com.example.FootBall.databinding.ActivityPostBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val deleteButton=binding.postDeleteButton
        val postTitle = binding.postTitle
        val postDate = binding.postDate
        val postAuther = binding.postAuther
        val postContent = binding.postContent
        val postImage = binding.postImageView

        var post: Post?=null

        // 인텐트를 통해 받은 데이터 표시
        val postPath = intent.getStringExtra("postPath")
        // 받은 게시글 경로가 null이면 종료.
        if(postPath == null){
            Log.d("dfdf","불러오기에 실패함5.")
            finish()
        }
        //게시물경로로 게시물(post객체)받아오기
        FireStoreConnection.onGetDocument(postPath!!) { document ->
            post = document.toObject(Post::class.java)

            if (post == null)//게시글을 받아와도 안에든게 없으면 종료.
            {
                Log.d("dfdf", "불러오기에 실패함t=8.")
                finish()
            }
            //post 객체안의 데이터를 뺴내어 화면에 표시하기.
            postTitle.text = post?.title ?: ""
            postDate.text = post?.title ?: ""
            postAuther.text = "작성자 : "+post?.author ?: ""
            postContent.text = post?.content ?: ""
            // postListItem안에 있는 Timestamp를 date로 변환해 표기하기
            val date = Date(post?.timestamp ?: 0)
            // 날짜 포맷 설정
            //val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss", Locale.getDefault())
            val formattedDate = dateFormat.format(date)
            postDate.text = formattedDate


            //화면에 이미지 띄우기
            if(post?.imagePath != null)
            {
                FireStorageConnection.bindImageByPath(this,post!!.imagePath!!,postImage)
            }
            else
            //이미지가 없으면 이미지뷰를 안보이게한다.
                postImage.visibility = ImageView.INVISIBLE
        }

        //게시글 삭제버튼
        deleteButton.setOnClickListener{
            //그냥 바로 게시글 삭제하기  (나중에 이곳에 사용자 인증코드 추가)
            FireStoreConnection.documentDelete(postPath){
                success ->
                if(success){//현재문서 삭제에 성공했다면
                    //이미지 문서가 없는 경우엔 바로 게시글 삭제완료처리
                    if(post!!.imagePath==null || post!!.imagePath==""){
                        Toast.makeText(this,"게시글 삭제완료",Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else
                    {
                        //안에 들어있는 이미지도 파이어 스토리지에 삭제하기
                        FireStorageConnection.deleteFile(post!!.imagePath!!){
                                success->
                            //이미지 삭제에 성공했다면
                            if(success){
                                Toast.makeText(this,"게시글 삭제완료",Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            //이미지 삭제에 실패했다면
                            else{
                                Toast.makeText(this,"이미지삭제오류",Toast.LENGTH_SHORT).show()
                                finish()
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(this,"게시글 삭제실페",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}