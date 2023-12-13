package com.example.lazylistsdialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazylistsdialog.ui.theme.LazyListsDialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyListsDialogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Create a LazyList of tasks
                    val tasks = remember { mutableStateListOf<String>() }

                    LazyListWithAlertDialogPage(tasks)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyListWithAlertDialogPage(tasks: MutableList<String>) {

    // State to track whether the AlertDialog is shown
    var showDialog by remember { mutableStateOf(false) }

    // State to hold the input text from the AlertDialog
    var inputText by remember { mutableStateOf(TextFieldValue()) }

    // Composable that represents the main UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Button to show the AlertDialog
        Button(onClick = { showDialog = true }) {
            Text("Add Task")
        }

        // Title for the LazyColumn
        Text(
            text = "Task List",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // LazyColumn to display tasks
        LazyColumn {
            items(tasks) { task ->
                Text(text = task, modifier = Modifier.padding(8.dp))
            }
        }

        // AlertDialog for adding tasks
        if (showDialog) { //on click
            AlertDialog(
                onDismissRequest = {
                    // Handle dismiss
                    showDialog = false
                },
                title = {
                    Text(text = "Add Task")
                },
                text = {
                    // Input text field in the AlertDialog
                    TextField(
                        value = inputText,
                        onValueChange = {
                            inputText = it
                        },
                        label = { Text("Enter Task") }
                    )
                },
                confirmButton = {
                    // Submit button
                    IconButton(onClick = {
                        // Add the input text to the LazyList
                        tasks.add(inputText.text)
                        // Reset the input text
                        inputText = TextFieldValue()
                        // Dismiss the AlertDialog
                        showDialog = false
                    }) {
                        Icon(imageVector = Icons.Default.Done, contentDescription = "Submit")
                    }
                },
                dismissButton = {
                    // Dismiss button
                    IconButton(onClick = {
                        // Handle dismiss
                        showDialog = false
                    }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Dismiss")
                    }
                }
            )
        }
    }
}