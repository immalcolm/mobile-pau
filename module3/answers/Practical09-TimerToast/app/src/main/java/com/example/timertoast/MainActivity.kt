package com.example.timertoast

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timertoast.ui.theme.TimerToastTheme
import java.util.Timer
import java.util.TimerTask

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerToastTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TimerScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun TimerScreen() {
    // Obtain the current context using LocalContext
    val context = LocalContext.current

    // State to track whether the timer is running
    var timerRunning by remember { mutableStateOf(false) }

    // Create a single Timer object
    var timer by remember { mutableStateOf(Timer()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text to display the timer status
        Text(text = if (timerRunning) "Timer is Running" else "Timer is Stopped")

        // Spacer to add padding between Text and Button
        Spacer(modifier = Modifier.height(16.dp))

        // Button to start/stop the timer
        Button(
            onClick = {
                timerRunning = !timerRunning //invert the status of the timer
                if (timerRunning) {
                    // Start the timer
                    runTimer(context, timer) //run the timer
                } else {
                    // Stop the timer
                    timer.cancel() //"killed"
                    timer = Timer() //re-init our timer
                }
            }
        ) {
            Text(text = if (timerRunning) "Stop Timer" else "Start Timer")
        }
    }
}

private fun runTimer(context: Context, timer: Timer) {
    // Schedule a specified task for repeated fixed-delay execution
    timer.schedule(object : TimerTask() {
        override fun run() {
            // Run the task on the main thread using Handler
            Handler(Looper.getMainLooper()).post {
                showToast(context, "Task will run for 3 seconds...")
            }
        }
    }, 0, 3000) // Run the task every 3000 milliseconds (3 seconds)
}

private fun showToast(context: Context, message: String) {
    // Show a toast notification using the provided context
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}