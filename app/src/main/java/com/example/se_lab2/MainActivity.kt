package com.example.se_lab2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.se_lab2.SpeechHelper.convertStringActionToBoolean
import com.example.se_lab2.ui.screens.controls.ControlsScreen
import com.example.se_lab2.ui.screens.controls.ControlsViewModel
import com.example.se_lab2.ui.theme.Se_Lab2Theme
import java.util.Locale


class MainActivity : ComponentActivity() {

    private val controlsViewModel by viewModels<ControlsViewModel> { ControlsViewModel.provideFactory() }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Se_Lab2Theme {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { startSpeechRecognizer() },
                        ) {
                            Icon(painterResource(R.drawable.light_on), contentDescription = "Speech recognition button")
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center
                ) { contentPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ControlsScreen(controlsViewModel)
                    }
                }
                // A surface container using the 'background' color from the theme

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val spokenText: String? =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                    results?.get(0)
                }
            if (spokenText != null) handleSpokenText(spokenText)

        }
    }

    private fun startSpeechRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        }
        // This starts the activity and populates the intent with the speech text.
        startActivityForResult(intent, SPEECH_REQUEST_CODE)
    }

    private fun handleSpokenText(text: String?) {
        if (text == null) return

        // Split words array into list of lowercase words
        val words = text.split(" ").map { it.lowercase() }
        // Set device to an available device in deviceActions list or null if not found
        val device = words.find { SpeechHelper.deviceActions.keys.contains(it) }
        // Get possible actions list from deviceActions map for device
        val availableActions = SpeechHelper.deviceActions[device] ?: emptyList()
        // Find an action that matches the input text in the devices available actions
        val actionText = words.find { availableActions.contains(it.lowercase()) }
        // If device or actionText are null, early return to exit the method
        if (device.isNullOrEmpty() || actionText.isNullOrEmpty()) return
        // Convert the action text to boolean value, if null no action available. return to exit the method
        val newState: Boolean = String.convertStringActionToBoolean(actionText) ?: return
        // Update the database
        controlsViewModel.toggleDeviceStatus(device.lowercase(), newState)
    }

    companion object {
        private const val SPEECH_REQUEST_CODE = 0
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Se_Lab2Theme {
        //ControlsScreen()
    }
}