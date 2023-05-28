package com.example.se_lab2.ui.screens.controls.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.se_lab2.data.Device

@Composable
fun DeviceCard(device: Device, onToggle: (String, Boolean) -> Unit) {
    Card {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 20.dp)
            ){
                Text(
                    text = "${device.type.replaceFirstChar { it.uppercase() }}: ",
                    fontWeight = FontWeight.Bold,
                )
                device.state?.let {
                    Text(
                        text = it,
                        color = if (device.isActive) Color(0xFF055703) else Color.Red
                    )
                }
            }
            Row(
                modifier = Modifier.weight(2f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                device.image?.let {
                    Icon(
                        painterResource(it),
                        contentDescription = null
                    )
                }
                Switch(
                    checked = device.isActive,
                    onCheckedChange = {
                        onToggle(device.type, it)
                    }
                )
            }
        }
    }
}