package com.pau.simpletimerdemokotlin

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pau.simpletimerdemokotlin.ui.theme.SimpleTimerDemoKotlinTheme
import kotlinx.coroutines.delay
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleTimerDemoKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StopwatchTimer()
                }
            }
        }
    }
}
@Composable
fun StopwatchTimer() {
    var isRunning by remember { mutableStateOf(false) }
    var elapsedMillis by remember { mutableStateOf(0L) }
    val coroutineScope = rememberCoroutineScope()

    //LaunchedEffect and Coroutine: When isRunning is true, a
    // coroutine periodically increments elapsedMillis.
    // delay(100L) determines the update frequency
    // (every 100 milliseconds in this example).
    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (isRunning) {
                delay(100L) // Update interval
                elapsedMillis += 100L
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = formatTime(elapsedMillis), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { isRunning = !isRunning }) {
                Text(if (isRunning) "Stop" else "Start")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { elapsedMillis = 0L }) {
                Text("Reset")
            }
        }
    }
}

fun formatTime(millis: Long): String {
    val seconds = millis / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    return String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60)
}

