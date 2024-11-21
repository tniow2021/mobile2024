package com.example.mobileproject

import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

open class FireStoreConnection {
    companion object {
        open fun onGetCollection(collection: String,callBack:(documents:List<DocumentSnapshot>)->Unit) {
            val db=FirebaseFirestore.getInstance()
            db.collection(collection)
                .get()
                .addOnSuccessListener { result ->
                    callBack(result.documents)
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

        //문서삭제하기 ( 삭제여부를 담아 콜백호출)
        open fun documentDelete(documentPath:String,callBack:(success:Boolean)->Unit)
        {
            val db=FirebaseFirestore.getInstance()
            db.document(documentPath)
                .delete()
                .addOnSuccessListener {
                    callBack(true)
                }
                .addOnFailureListener{
                    callBack(false)
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