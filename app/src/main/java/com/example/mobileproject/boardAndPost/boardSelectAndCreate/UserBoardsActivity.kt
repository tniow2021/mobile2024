package com.example.mobileproject.boardAndPost.boardSelectAndCreate

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
            for (document in documents) {

                val board = document.toObject(BoardListItem::class.java)
                if (board != null)
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

        //어댑터 만들기
        adapter = BoardListAdapter(this, R.layout.item_board_preview,
            boardList,"userBoards/")

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

        refresh()

    }
}