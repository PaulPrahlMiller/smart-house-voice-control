package com.example.se_lab2.ui.screens.controls

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.se_lab2.data.Device
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.values
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ControlsViewModel(private val database: DatabaseReference): ViewModel() {

    private val _uiState = MutableStateFlow(ControlsUiState())
    val uiState: StateFlow<ControlsUiState> = _uiState.asStateFlow()

    init {
        database.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.i("onDataChangeLog", snapshot.toString())
                val devices = mutableListOf<Device>()
                snapshot.child("Devices").children.map {
                    if (it.key.isNullOrEmpty()) return // If key is null, points to database root
                    devices.add(Device(it.key!!, it.value as Boolean))
                }
                _uiState.compareAndSet(_uiState.value, ControlsUiState(devices))
            }

            override fun onCancelled(error: DatabaseError) {
                // TODO("Not yet implemented")
            }
        })
    }

    fun toggleDeviceStatus(type: String, isActive: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            database.child("Devices").child(type).setValue(isActive)
        }
    }

    companion object {
        fun provideFactory(
            database: DatabaseReference
        ) : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ControlsViewModel(database) as T
            }
        }
    }
}

data class ControlsUiState(
    val devices: List<Device> = emptyList()
)