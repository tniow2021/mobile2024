package com.example.mobileproject

import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

open class FireStoreConnection {
    companion object {
        //private val db: FirebaseFirestore
        //private val storage: FirebaseStorage
/*
        init {
            Log.d("fseffes", "00000")
            //db = FirebaseFirestore.getInstance()
            //storage = FirebaseStorage.getInstance()
        }


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
        open fun onGetCollection(collection: String,callBack:(documents:List<DocumentSnapshot>)->Unit) {
            val db=FirebaseFirestore.getInstance()
            db.collection(collection)
                .get()
                .addOnSuccessListener { result ->
                    Log.d("fgurhguhr","gooooooooood")
                    callBack(result.documents)
                    Log.d("fgurhguhr","gooooooooood")
                }
        }
        open fun onGetDocument(documentPath:String,callBack:(document:DocumentSnapshot)->Unit)
        {
            Log.d("onGetDocument",documentPath)
            val db=FirebaseFirestore.getInstance()
            db.document(documentPath)
                .get()
                .addOnSuccessListener {
                    document->
                    if(document.exists())//만약 문서가 존재하면
                    {
                        callBack(document)
                    }
                    else
                    {
                        Log.d("onGetDocument","failure")
                    }
                }
        }

        //성공여부를 담아보내주는 콜백이 매개변수로 있음.
        open fun addDocument(collection: String,post:Any,
                             isCompleted:(success:Boolean,docPath:String)->Unit)
        {
            val db=FirebaseFirestore.getInstance()
            db.collection(collection)
                .add(post)
                .addOnSuccessListener {
                    documentReference->

                    isCompleted(true,documentReference.path)
                }
                .addOnFailureListener {
                    isCompleted(false,"")
                }
        }

    }
}