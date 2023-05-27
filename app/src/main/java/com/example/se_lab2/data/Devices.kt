package com.example.se_lab2.data

import com.example.se_lab2.R

data class Device(
    val type: String = "",
    val isActive: Boolean = true,
) {
    val image: Int
        get() = if (isActive) Devices.activeIcons[type]!! else Devices.nonActiveIcons[type]!!
    val state: String
        get() = if (isActive) Devices.activeStates[type]!! else Devices.nonActiveStates[type]!!
}

object Devices {
    val all: List<Device> = listOf(
        Device(type = "light", isActive = true),
        Device(type = "door", isActive = true),
        Device(type = "window", isActive = false)
    )

    val activeStates: Map<String, String> = mapOf(
        "light" to "On",
        "door" to "Open",
        "window" to "Open"
    )

    val nonActiveStates: Map<String, String> = mapOf(
        "light" to "Off",
        "door" to "Closed",
        "window" to "Closed"
    )

    val activeIcons: Map<String, Int> = mapOf(
        "light" to R.drawable.light_on,
        "door" to R.drawable.door_open,
        "window" to R.drawable.window_open
    )

    val nonActiveIcons: Map<String, Int> = mapOf(
        "light" to R.drawable.light_off,
        "door" to R.drawable.door_closed,
        "window" to R.drawable.window_closed
    )

}
