package com.example.introducingme_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.introducingme_kotlin.ui.theme.IntroducingMe_KotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntroducingMe_KotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Preview
@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "first_screen") {
        // Define the FirstScreen with the route "first_screen"
        composable("first_screen") {
            FirstScreen(navController)
        }

        // Define the SecondScreen with the route "second_screen/{name}/{hobby}/{age}"
        composable(
            route = "second_screen/{name}/{hobby}/{age}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType; defaultValue = ""   },
                navArgument("hobby") { type = NavType.StringType; defaultValue = ""  },
                navArgument("age") { type = NavType.StringType; defaultValue = ""  }
            )
        ) { backStackEntry ->
            // Extract the data from the arguments
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val hobby = backStackEntry.arguments?.getString("hobby") ?: ""
            val age = backStackEntry.arguments?.getString("age") ?: ""
            SecondScreen(navController, name, hobby, age)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var hobby by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Input field for entering name
        OutlinedTextField(
            value = name,
            onValueChange = { newName -> name = newName },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(8.dp)
        )

        // Input field for entering hobby
        OutlinedTextField(
            value = hobby,
            onValueChange = { newHobby -> hobby = newHobby },
            label = { Text("Hobby") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(8.dp)
        )

        // Input field for entering age
        OutlinedTextField(
            value = age,
            onValueChange = { newAge -> age = newAge },
            label = { Text("Age") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(8.dp)
        )

        // Button to navigate to the next screen and pass data
        Button(
            onClick = {
                // Navigate to the next screen and pass the data
                navController.navigate("second_screen/$name/$hobby/$age")

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Next Screen")
        }
    }
}

@Composable
fun SecondScreen(
    navController: NavController,
    name: String,
    hobby: String,
    age: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display the received data
        Text("Name: $name", style = MaterialTheme.typography.titleMedium)
        Text("Hobby: $hobby", style = MaterialTheme.typography.titleMedium)
        Text("Age: $age", style = MaterialTheme.typography.titleMedium)

        // Button to navigate back to the previous screen
        Button(
            onClick = {
                // Navigate back to the previous screen
                navController.navigate("first_screen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Go Back")
        }
    }
}


