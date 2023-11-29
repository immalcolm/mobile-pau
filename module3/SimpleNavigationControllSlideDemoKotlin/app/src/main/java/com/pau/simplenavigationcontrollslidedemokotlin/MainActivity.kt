package com.pau.simplenavigationcontrollslidedemokotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pau.simplenavigationcontrollslidedemokotlin.ui.theme.SimpleNavigationControllSlideDemoKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleNavigationControllSlideDemoKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    // Create the NavController
    val navController = rememberNavController()

    // Set up the NavHost with the NavController
    NavHost(navController = navController, startDestination = "first_screen") {
        // Define the FirstScreen with the route "first_screen"
        composable("first_screen") {
            FirstScreen(navController)
        }
        // Define the SecondScreen with the route "second_screen/{data}"
        composable(
            route = "second_screen/{data}",
            arguments = listOf(navArgument("data") { type = StringType })
        ) { backStackEntry ->
            // Extract the data from the arguments
            val data = backStackEntry.arguments?.getString("data") ?: ""
            SecondScreen(navController, data)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // To use with TextField
@Composable
fun FirstScreen(navController: NavController) {
    var data by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Input field for entering data
        TextField(
            value = data,
            onValueChange = { newData -> data = newData },
            label = { Text("Enter Data") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

// Navigate to SecondScreen with the entered data
        Button(
            onClick = {
                navController.navigate("second_screen/$data")
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Go to SecondScreen")
        }
    }
}

@Composable
fun SecondScreen(navController: NavController, data: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display the received data
        Text("Received Data: $data")
        // Navigate back to FirstScreen
        Button(
            onClick = {
                navController.navigate("first_screen")
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Go back to FirstScreen")
        }
    }
}

