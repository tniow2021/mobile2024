package com.example.mobileproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PostListAdapter
    (context: Context,
    private val resource: Int,
    private val postList: List<Post>)
    :ArrayAdapter<Post>(context, resource, postList)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // 재사용 가능한 View를 가져오기 (ViewHolder 패턴 사용)
        val view: View = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)

        // 데이터 바인딩
        val post = postList[position]
        val titleTextView: TextView = view.findViewById(R.id.postListItem_title)
        val authorTextView: TextView = view.findViewById(R.id.postListItem_auther)

        titleTextView.text = post.title
        authorTextView.text = post.author

        return view
    }

}