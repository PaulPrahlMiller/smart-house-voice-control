package com.example.se_lab2.data

import com.google.firebase.database.FirebaseDatabase

class Repository(val database: FirebaseDatabase) {

    var devices: List<Device> = emptyList()

    suspend fun getDevices(): List<Device> {
        database.getReference("isLightOn")
        return Devices.all
    }

    suspend fun updateLightStatus(isActive: Boolean) {

    }
}