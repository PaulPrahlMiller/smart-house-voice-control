package com.example.se_lab2.ui.screens.controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.se_lab2.data.Devices
import com.example.se_lab2.ui.screens.controls.composables.DeviceCard

@Composable
fun ControlsScreen(controlsViewModel: ControlsViewModel) {
    val state = controlsViewModel.uiState.collectAsState()
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp)){
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding()
                    .padding(bottom = 30.dp),
                textAlign = TextAlign.Center,
                text = "Home Control Center",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ){
                items(state.value.devices) {
                    DeviceCard(it, controlsViewModel::toggleDeviceStatus)
                }
            }
        }
    }
}

@Composable
fun ControlsScreenContent(
    state: ControlsUiState,
) {

}

@Preview(showBackground = true)
@Composable
fun ControlsScreenPreview() {
    ControlsScreenContent(ControlsUiState(devices = Devices.all))
}