package com.example.se_lab2

object SpeechHelper {
    val deviceActions: Map<String, List<String>> = mapOf(
        "light" to listOf("on", "off"),
        "door" to listOf("open", "close"),
        "window" to listOf("open", "close")
    )

    fun String.Companion.convertStringActionToBoolean(text: String): Boolean? {
        var boolean: Boolean? = null
        when(text) {
            "on" -> boolean = true
            "open" -> boolean = true
            "off" -> boolean = false
            "close" -> boolean = false
        }
        return boolean
    }
}