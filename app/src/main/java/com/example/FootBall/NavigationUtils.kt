package com.example.FootBall

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.FootBall.football_minjae.MyProfileActivity
import com.example.FootBall.football_minjae.TeamListActivity

object NavigationUtils {

    fun setupNavigationBar(activity: Activity, rootView: View) {
        val teamListButton: Button = rootView.findViewById(R.id.btn_team_list)
        val myProfileButton: Button = rootView.findViewById(R.id.btn_my_profile)

        teamListButton.setOnClickListener {
            if (activity !is TeamListActivity) {
                val intent = Intent(activity, TeamListActivity::class.java)
                activity.startActivity(intent)
            }
        }

        myProfileButton.setOnClickListener {
            if (activity !is MyProfileActivity) {
                val intent = Intent(activity, MyProfileActivity::class.java)
                activity.startActivity(intent)
            }
        }
    }
}
