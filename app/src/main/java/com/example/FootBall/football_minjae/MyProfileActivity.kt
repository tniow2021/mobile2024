package com.example.FootBall.football_minjae

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.FootBall.FireStorageConnection
import com.example.FootBall.NavigationUtils
import com.example.FootBall.R

class MyProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        val navigationBar = findViewById<View>(R.id.navigation_bar)
        NavigationUtils.setupNavigationBar(this, navigationBar)

        val app = application as MyApplication
        val user = app.currentUser
        if (user != null) {
            FireStorageConnection.bindImageByPath(this, user.profile, findViewById(R.id.imageViewProfile))
            findViewById<TextView>(R.id.textViewName).text = user.name
            findViewById<TextView>(R.id.textViewInfo).text = user.info
            findViewById<TextView>(R.id.textViewTeam).text = user.team
        }
        else {
            Toast.makeText(this, "사용자 데이터를 읽어오지 못하였습니다\n로그아웃 후 다시 로그인해주세요", Toast.LENGTH_SHORT).show()
            findViewById<TextView>(R.id.textViewName).text = "이름"
            findViewById<TextView>(R.id.textViewInfo).text = "자기소개"
            findViewById<TextView>(R.id.textViewTeam).text = "팀"
        }

        val editProfileButton = findViewById<Button>(R.id.btnEditProfile)
        val logoutButton = findViewById<Button>(R.id.logout)

        editProfileButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)

            finish()
        }

        logoutButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("AutoLogin", MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()

            // 로그인 화면으로 이동
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            finish()
        }

        val addButton = findViewById<ImageView>(R.id.addButton)
        addButton.setOnClickListener {
            Toast.makeText(this, "구현 예정", Toast.LENGTH_SHORT).show()
        }

        // 최근 방문 경기 리스트 (임의 데이터)
        val recentMatches = listOf(
            Match("FC 서울 vs 수원 FC", "2024-11-01", R.drawable.suwon),
            Match("전북 FC vs FC 서울", "2024-10-20", R.drawable.jeonbuk),
            Match("전북 FC vs 수원 FC", "2024-09-15", R.drawable.fc_seoul)
        )

        // 최근 방문 경기 추가2
        val recentMatchesLayout = findViewById<LinearLayout>(R.id.layoutRecentMatches)
        for (match in recentMatches) {
            val matchView = createMatchView(match)
            recentMatchesLayout.addView(matchView)
        }
    }

    // 경기 항목 뷰 생성
    private fun createMatchView(match: Match): View {
        val itemView = layoutInflater.inflate(R.layout.item_match, null)

        val matchImageView = itemView.findViewById<ImageView>(R.id.imageViewMatchIcon)
        val matchNameTextView = itemView.findViewById<TextView>(R.id.textViewMatchName)
        val matchDateTextView = itemView.findViewById<TextView>(R.id.textViewMatchDate)

        matchImageView.setImageResource(match.iconResId)
        matchNameTextView.text = match.name
        matchDateTextView.text = match.date

        return itemView

    }

    // 경기 데이터 클래스
    data class Match(
        val name: String,
        val date: String,
        val iconResId: Int
    )
}
