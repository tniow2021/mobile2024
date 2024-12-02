package com.example.FootBall.football_minjae

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.FootBall.FireStorageConnection
import com.example.FootBall.FireStoreConnection
import com.example.FootBall.R
import com.yalantis.ucrop.UCrop
import java.io.File

class EditActivity : AppCompatActivity() {

    private var croppedImageUri: Uri? = null
    private lateinit var profileImageView: ImageView
    private val teamList = MainTeamList().getMainTeamList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val back = findViewById<LinearLayout>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, MyProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            finish() // 현재 Activity 종료 (뒤로가기)
        }

        val app = application as MyApplication
        val user = app.currentUser

        // UI 요소 초기화
        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val infoEditText: EditText = findViewById(R.id.infoEditText)
        val teamEditText: EditText = findViewById(R.id.teamEditText)

        // 글자수 제한 설정
        nameEditText.filters = arrayOf(InputFilter.LengthFilter(11)) // 이름은 최대 10자
        infoEditText.filters = arrayOf(InputFilter.LengthFilter(21)) // 자기소개는 최대 20자
        profileImageView = findViewById(R.id.profileImageView)

        val selectImageButton: Button = findViewById(R.id.selectImageButton)
        val saveButton: Button = findViewById(R.id.saveButton)

        // 기존 데이터 세팅
        nameEditText.setText(user?.name)
        infoEditText.setText(user?.info)
        teamEditText.setText(user?.team)
        user?.profile?.let { FireStorageConnection.bindImageByPath(this, it, profileImageView) }

        // 선택 가능한 목록
        var teams = arrayOf("없음")

        for (teamList in teamList) {
            teams += teamList.name
        }

        // EditText 클릭 시 AlertDialog 표시
        teamEditText.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("팀 선택")
            builder.setItems(teams) { _, which ->
                // 사용자가 선택한 이름을 EditText에 설정
                teamEditText.setText(teams[which])
            }
            builder.show()
        }


        nameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length ?: 0 > 10) {
                    Toast.makeText(this@EditActivity, "최대 10자까지 입력 가능합니다", Toast.LENGTH_SHORT).show()
                    nameEditText.setText(s?.substring(0, 10)) // 20자 초과 시 잘라내기
                    nameEditText.setSelection(10) // 커서를 마지막으로 이동
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        infoEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length ?: 0 > 20) {
                    Toast.makeText(this@EditActivity, "최대 20자까지 입력 가능합니다", Toast.LENGTH_SHORT).show()
                    infoEditText.setText(s?.substring(0, 20)) // 20자 초과 시 잘라내기
                    infoEditText.setSelection(20) // 커서를 마지막으로 이동
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })


        // 이미지 선택 버튼 클릭 이벤트
        selectImageButton.setOnClickListener {
            openImagePicker()
        }

        // 저장 버튼 클릭 이벤트
        saveButton.setOnClickListener {
            if (user != null) {
                user.name = nameEditText.text.toString()
                user.info = infoEditText.text.toString()
                user.team = teamEditText.text.toString()

                // 크롭된 이미지 URI를 저장
                if (croppedImageUri != null) {
                    FireStorageConnection.addFile("users/${user.email}", croppedImageUri) { success, filePath ->
                        user.profile = filePath // 실제로는 Firebase Storage 업로드 후 URL 저장

                        FireStoreConnection.setDocument("users/${user.email}", user) { success, _ ->
                            if (success) {
                                Toast.makeText(this, "수정 성공", Toast.LENGTH_SHORT).show()
                                app.currentUser = user
                                Log.d("srersr",app.currentUser!!.profile)
                            } else {
                                Toast.makeText(this, "수정 실패", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                else {
                    FireStoreConnection.setDocument("users/${user.email}", user) { success, _ ->
                        if (success) {
                            Toast.makeText(this, "수정 성공", Toast.LENGTH_SHORT).show()
                            app.currentUser = user
                        } else {
                            Toast.makeText(this, "수정 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            val intent = Intent(this, MyProfileActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICKER_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                startCropActivity(uri)
            }
        } else if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
            UCrop.getOutput(data!!)?.let { uri ->
                croppedImageUri = uri
                profileImageView.setImageURI(uri) // 크롭된 이미지 표시
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val cropError = UCrop.getError(data!!)
            Toast.makeText(this, "이미지 크롭 실패: ${cropError?.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startCropActivity(sourceUri: Uri) {
        val destinationUri = Uri.fromFile(File(cacheDir, "croppedImage.jpg"))

        UCrop.of(sourceUri, destinationUri)
            .withAspectRatio(1f, 1f) // 정사각형 비율
            .withMaxResultSize(500, 500) // 최대 크기 설정
            .start(this)
    }

    companion object {
        private const val IMAGE_PICKER_REQUEST_CODE = 1001
    }
}
