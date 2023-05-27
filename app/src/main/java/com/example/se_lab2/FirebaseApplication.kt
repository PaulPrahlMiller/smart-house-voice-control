package com.example.se_lab2

import android.app.Application
import com.example.se_lab2.data.Repository
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}