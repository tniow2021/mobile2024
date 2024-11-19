package com.example.mobileproject

import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Locale

open class FireStoreConnection {
    companion object {
        private val db: FirebaseFirestore
        private val storage: FirebaseStorage

        init {
            Log.d("fseffes", "00000")
            db = FirebaseFirestore.getInstance()
            storage = FirebaseStorage.getInstance()
        }

        /*
    //    open inline fun <reified T>  getDocument(list : List<T>,route:String){
    //        db.collection(route)
    //            .get()
    //            .addOnCompleteListener { task ->
    //                if (task.isSuccessful) {
    //                    val document = task.result
    //                    if (document != null && document.exists()) {
    //                        val post = document.toObject(T::class.java)
    //                    }
    //                } else {
    //                    Log.d("Firestore", "Error getting document: ", task.exception)
    //                }
    //            }
    //    }

         */
        open fun getDocuments(collectionRoute: String): List<DocumentSnapshot>? {
            var temp: List<DocumentSnapshot>? = null
            db.collection(collectionRoute)
                .get()
                .addOnSuccessListener { result ->
                    Log.d("fgurhguhr","gooooooooood")
                    temp=result.documents
                    Log.d("fgurhguhr","gooooooooood")
                }
            return temp
        }
    }
}