package com.example.kotlin_preferences_data_store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_preferences_data_store.datastore.StoreBackgroundColour
import com.example.kotlin_preferences_data_store.ui.theme.Kotlin_Preferences_Data_StoreTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kotlin_Preferences_Data_StoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    PreferencesScreen()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferencesScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    // Avoid creating more than 1 instance of this DataStore
    val dataStore = StoreBackgroundColour(context)

    //get saved colour & status
    val savedBackgroundColour = dataStore.getBackgroundColour.collectAsState(initial = "").value
    val savedSwitchStatus = dataStore.isSwitchOn.collectAsState(initial = false).value
    var backgroundColour by remember { mutableStateOf("") }
    var isOn by remember { mutableStateOf(false) }

    var bgColour = if (savedSwitchStatus) Color(
        android.graphics.Color.parseColor(
            savedBackgroundColour
        )
    ) else Color.White

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(bgColour),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                value = backgroundColour,
                onValueChange = {
                    backgroundColour = it
                },
                label = { Text("New Color code") },
                placeholder = { Text("Enter color code in hexadecimal format")}
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 16.dp, end = 16.dp),
                onClick = {
                    // we have to call the saveBackgroundColour() in a Kotlin Coroutine
                    // launch the class in a coroutine scope
                    scope.launch {
                        dataStore.saveBackgroundColour(backgroundColour)
                    }
                },
            ) {
                // button text
                Text(
                    text = "Save",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
            Text(
                text = "Colour Saved: " + savedBackgroundColour!!,
                color = Color.Blue,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Toggle Background Color",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Switch(
                checked = savedSwitchStatus,
                onCheckedChange = {
                    isOn = it
                    scope.launch {
                        dataStore.setSwitchOn(isOn)
                    }
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.White
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreferencesScreenPreview() {
    Kotlin_Preferences_Data_StoreTheme {
        PreferencesScreen()
    }
}