package com.example.mobileproject.boardAndPost.boardSelectAndCreate

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileproject.FireStorageConnection
import com.example.mobileproject.FireStoreConnection
import com.example.mobileproject.R
import com.example.mobileproject.boardAndPost.BoardActivity
import com.example.mobileproject.databinding.ActivityUserBoardsBinding

class UserBoardsActivity : AppCompatActivity() {
    private val boardList= ArrayList<BoardListItem>()
    private lateinit var adapter: BoardListAdapter

    private fun refresh()
    {
        FireStoreConnection.onGetCollection("userBoards/") { documents ->
            boardList.clear()
            for (document in documents) {

                val board = document.toObject(BoardListItem::class.java)
                Log.d("UserBoardsActivity",board!!.boardName)
                if(board.boardName==""){ // 만약 문서를 가져왔는데 아무 데이터가 없는 빈 문서면 무시
                    continue
                }
                boardList.add(board)
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding=ActivityUserBoardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listView:ListView=binding.userBoardsListView
        val searchButton=binding.userBoardsSearchButtun
        val searchEditText=binding.userBoardsEditText
        val deleteButton=binding.userBoardsDeleteButtun

        //어댑터 만들기
        adapter = BoardListAdapter(this, R.layout.item_board_preview,
            boardList,"userBoards/")
        listView.adapter=adapter

        //새로고침
        refresh()

        // 리스트 아이템 클릭 시 게시글 화면으로 이동
        listView.setOnItemClickListener { _, _, position, _ ->
            // 클릭된 게시판 객체
            val board = boardList[position]
            // 게시글 화면으로 이동
            val myintent = Intent(applicationContext, BoardActivity::class.java)
            myintent.putExtra("boardPath","userBoards/"+board.boardName)
            myintent.putExtra("boardName",board.boardName)
            startActivity(myintent)
        }

        //게시판 검색
        searchButton.setOnClickListener{
            var searchTxt=searchEditText.text.toString()
            if(searchTxt==""){
                //searchEditText에 입력된 값이 없으면 게시판 새로고침하고 토스트띄우기
                refresh()
                Toast.makeText(this,"검색어를 입력해주십시요",Toast.LENGTH_SHORT).show()
            }
            else//검색
            {
                FireStoreConnection.onGetDocument("userBoards/"+searchTxt)
                {
                    success, document ->
                    if(success){
                        boardList.clear()
                        val board = document!!.toObject(BoardListItem::class.java)
                        Log.d("UserBoardsActivity",board!!.boardName)
                        boardList.add(board)
                        adapter.notifyDataSetChanged()
                    }
                    else{
                        Toast.makeText(this,"검색된 내용이 없습니다.",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        //게시판 삭제버튼
        deleteButton.setOnClickListener{
            // 레이아웃을 인플레이트
            val dialogView = LayoutInflater.from(this).inflate(R.layout.delete_board_window, null)

            // 다이얼로그 생성
            val dialog = AlertDialog.Builder(this)
                .setView(dialogView) // 커스텀 레이아웃 설정
                .setCancelable(true) // 다이얼로그 밖을 클릭하면 닫히게 설정 (true로 설정)
                .create()

            // 다이얼로그의 버튼 동작 설정
            val boardNameEditText = dialogView.findViewById<EditText>(R.id.deleteBoardWindow_EditText)
            val deleteButton = dialogView.findViewById<Button>(R.id.deleteBoardWindow_deleteButton)
            val cancelButton = dialogView.findViewById<Button>(R.id.deleteBoardWindow_cancelButton)

            cancelButton.setOnClickListener {
                dialog.dismiss() // 다이얼로그 닫기
            }


            //  (중요) 삭제 버튼을 눌렀을때
            deleteButton.setOnClickListener{
                val boardNameEntered=boardNameEditText.text.toString()
                if(boardNameEntered != ""){
                    // (중요) 게시판 삭제하기
                    FireStoreConnection.documentDelete("userBoards/"+boardNameEntered)
                    {
                            success ->
                        if(success){
                            //스토리지 내 저장한 이미지들도 모두 삭제
                            FireStorageConnection.deleteDirectory("userBoards/"+boardNameEntered)
                            Toast.makeText(this,"게시판 삭제완료.",Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                        else{
                            Toast.makeText(this,"게시판 삭제실패.",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this,"입력한 내용이 없습니다.",Toast.LENGTH_SHORT).show()
                }
            }

            // 다이얼로그 띄우기
            dialog.show()

            // 다이얼로그 크기 조정 (중앙에 띄우기)
            val layoutParams = dialog.window?.attributes
            dialog.window?.attributes = layoutParams
        }

    }
}