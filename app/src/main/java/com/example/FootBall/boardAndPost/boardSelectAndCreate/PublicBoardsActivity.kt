package com.example.FootBall.boardAndPost.boardSelectAndCreate

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.FootBall.FireStoreConnection
import com.example.FootBall.R
import com.example.FootBall.boardAndPost.BoardActivity
import com.example.FootBall.databinding.ActivityPublicBoardsBinding

class PublicBoardsActivity : AppCompatActivity() {
    private val boardList= ArrayList<BoardListItem>()
    private lateinit var adapter: BoardListAdapter

    private fun refresh()
    {
        FireStoreConnection.onGetCollection("publicBoards/") { documents ->
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
        val binding=ActivityPublicBoardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listView:ListView=binding.publicBoardsListView

        //어댑터 만들기
        adapter = BoardListAdapter(this, R.layout.item_board_preview,
            boardList,"publicBoards/")
        listView.adapter=adapter
        // 리스트 아이템 클릭 시 게시글 화면으로 이동
        listView.setOnItemClickListener { _, _, position, _ ->
            // 클릭된 게시판 객체
            val board = boardList[position]
            // 게시글 화면으로 이동
            val myintent = Intent(applicationContext, BoardActivity::class.java)
            myintent.putExtra("boardPath","publicBoards/"+board.boardName)
            myintent.putExtra("boardName",board.boardName)
            startActivity(myintent)
        }

        refresh()

    }
}