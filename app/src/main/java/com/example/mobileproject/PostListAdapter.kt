package com.example.mobileproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostListAdapter
    (context: Context,
    private val resource: Int,
    private val postList: List<PostListItem>)
    :ArrayAdapter<PostListItem>(context, resource, postList)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // 재사용 가능한 View를 가져오기 (ViewHolder 패턴 사용)
        val view: View = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        // 데이터 바인딩
        val titleTextView: TextView = view.findViewById(R.id.postListItem_title)
        val authorTextView: TextView = view.findViewById(R.id.postListItem_auther)
        val dateTextView: TextView = view.findViewById(R.id.postListItem_date)

        val postListItem = postList[position]
        titleTextView.text = postListItem.title
        authorTextView.text = postListItem.author

        // postListItem안에 있는 Timestamp를 Date 객체로 변환
        val date = Date(postListItem.timestamp)

        // 날짜 포맷 설정
        //val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val dateFormat = SimpleDateFormat("MM-dd HH:mm:ss", Locale.getDefault())
        val formattedDate = dateFormat.format(date)

        dateTextView.text=formattedDate
        return view
    }

}