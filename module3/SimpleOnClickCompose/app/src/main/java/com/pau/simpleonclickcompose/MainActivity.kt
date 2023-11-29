package com.pau.simpleonclickcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pau.simpleonclickcompose.ui.theme.SimpleOnClickComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleOnClickComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleOnClickComposeTheme {
        Greeting("Android")
    }
}

//IconButton https://developer.android.com/reference/androidx/tv/material3/IconButtonKt?hl=en

@Composable
fun MainScreen() {
    //state to hold the current counter value
    //using var and remember
    var counter by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Counter: $counter", fontSize = 20.sp)

        //spacer
        Spacer(modifier = Modifier.height(16.dp))
        // Button to increment the counter
        IconButton(onClick = {
            counter++ // Increment the counter when the button is clicked
        }
        ) {
            // Icon representing the "Add" action
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Increment", // Accessibility description
                modifier = Modifier.size(48.dp) // Set the size of the icon
            )
        }

        // Spacer to add vertical space between elements
        Spacer(modifier = Modifier.height(16.dp))

        // Button to decrement the counter
        IconButton(onClick = {
            counter-- // Decrement the counter when the button is clicked
        }) {
            // Icon representing the "Remove" action
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Decrement", // Accessibility description
                modifier = Modifier.size(48.dp) // Set the size of the icon
            )
        }
    }
}