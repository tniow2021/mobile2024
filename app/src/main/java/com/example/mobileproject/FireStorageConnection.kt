package com.example.mobileproject

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

open class FireStorageConnection{
    companion object {
        open fun addFile(route: String, fileURL: Uri?, isCompleted: (success:Boolean,filePath: String) -> Unit) {

            if(fileURL == null)
                return

            val storage = FirebaseStorage.getInstance()
            val imageRef = storage.reference.child(route + "/" + UUID.randomUUID())
            imageRef.putFile(fileURL)
                .addOnSuccessListener {
                    // 이미지 업로드가 성공하면, 해당 이미지의 경로를 가져와서 반환해줌
                    isCompleted(true,imageRef.path)

                }
                .addOnFailureListener {
                    isCompleted(false,"")
                    Log.d("FireStorageConnection", "imageUproad failure")
                }
        }
    }
}