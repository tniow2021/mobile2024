package com.example.mobileproject

data class Post(val title: String, val author: String) {
    // String representation (리스트뷰에 출력될 텍스트)
    override fun toString(): String {
        return "$title ($author)"
    }
}