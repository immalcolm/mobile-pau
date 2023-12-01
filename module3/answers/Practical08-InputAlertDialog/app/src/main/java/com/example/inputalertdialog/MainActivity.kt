package com.example.inputalertdialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.inputalertdialog.ui.theme.InputAlertDialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InputAlertDialogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CustomDialog()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog() {
    // State to manage the visibility of the dialog
    var showDialog by remember { mutableStateOf(false) }

    // State to store the text entered in the TextField
    var textInput by remember { mutableStateOf(TextFieldValue()) }

    // State to store the text submitted from the dialog
    var submittedText by remember { mutableStateOf("Enter some text by clicking on the dialog") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text in the middle, displays the submitted text
        Text(
            text = submittedText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )

        // Button to call the dialog
        Button(onClick = { showDialog = true }) {
            Text(text = "Show Dialog")
        }

        // Dialog
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    // Handle dismiss
                    showDialog = false
                },
                title = {
                    Text(text = "Input Dialog")
                },
                text = {
                    // Input text field in the dialog
                    TextField(
                        value = textInput,
                        onValueChange = {
                            textInput = it
                        },
                        label = { Text("Enter Text") }
                    )
                },
                confirmButton = {
                    // Submit button
                    Button(
                        onClick = {
                            // Update the Text on the screen when the submit button is clicked
                            submittedText = textInput.text
                            showDialog = false
                            textInput = TextFieldValue("") // Clear the TextField
                        }
                    ) {
                        Text("Submit")
                    }
                },
                dismissButton = {
                    // Dismiss button
                    Button(
                        onClick = {
                            // Handle dismiss
                            showDialog = false
                        }
                    ) {
                        Text("Dismiss")
                    }
                }
            )
        }
    }
}


