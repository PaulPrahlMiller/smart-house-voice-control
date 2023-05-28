package com.example.se_lab2

import android.app.Application
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseApplication: Application() {

    lateinit var database: DatabaseReference

    override fun onCreate() {
        super.onCreate()
        database = Firebase.database.reference
    }

}