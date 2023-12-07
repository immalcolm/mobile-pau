package com.pau.simplekotlindatastore


import DataStoreRepo
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pau.simplekotlindatastore.ui.theme.SimpleKotlinDataStoreTheme
import kotlinx.coroutines.launch

const val USER_PREFERENCES_NAME = "settings"


//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
private val Context.dataStore by preferencesDataStore(name = "settings")
val EXAMPLE_COUNTER = intPreferencesKey("example_counter")

class MainActivity : ComponentActivity() {
    // At the top level of your kotlin file:
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleKotlinDataStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    PrefScreen()
                    //incrementCounter(LocalContext.current)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrefScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    //val dataStore = context.dataStore
    val dataStore = DataStoreRepo(context)

    //collectAsState is a Compose utility that collects a flow and represents its values as
    // state in your composable. It automatically updates the composable when the flow emits
    // new values.
    val counterValue by dataStore.counterFlow.collectAsState(initial = 0)

    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                //modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {

                Row() {
                    Button(
                        modifier = Modifier
                            //.fillMaxWidth()
                            .height(50.dp),
                        //  .padding(start = 16.dp, end = 16.dp),
                        onClick = {
                            // we have to call the incrementCounter() in a Kotlin Coroutine
                            // launch the class in a coroutine scope
                            scope.launch {
                                dataStore.incrementCounter()
                            }
                        },
                    ) {
                        // button text

                        /*
                        //manual way of getting the values from datastore
                    LaunchedEffect(true){
                        scope2.launch{
                            dataStore.counterFlow.collect { value ->
                                println("Read value: $value")
                            }
                        }
                    }
                    */

                        Text(
                            text = "Add +",
                            color = Color.White,
                            fontSize = 18.sp
                        )

                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        modifier = Modifier
                            //.fillMaxWidth()
                            .height(50.dp),
                        //  .padding(start = 16.dp, end = 16.dp),
                        onClick = {
                            // we have to call the incrementCounter() in a Kotlin Coroutine
                            // launch the class in a coroutine scope
                            scope.launch {
                                dataStore.decrementCounter()
                            }
                        },
                    ) {

                        Text(
                            text = "Minus -",
                            color = Color.White,
                            fontSize = 18.sp
                        )

                    }
                }
                Text("Counter value: $counterValue")
            }
        }
    }
}


suspend fun incrementCounter(context: Context) {
    context.dataStore.edit { settings ->
        val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
        settings[EXAMPLE_COUNTER] = currentCounterValue + 1
    }
}