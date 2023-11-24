package com.pau.simplelifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.pau.simplelifecycle.ui.theme.SimpleLifecycleTheme

const val TAG = "Simple Lifecycle"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleLifecycleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                    Greeting("Android")
                }
            }
        }
    }
}

/*
ComposableLifecycle { source, event -> ... } calls the ComposableLifecycle function,
passing a lambda function as the onEvent parameter.
This lambda function is triggered when a lifecycle event occurs.
The lambda function uses a when statement to check the type of the lifecycle event and
log a different message for each type of event. For example, when the ON_CREATE event occurs,
it logs "Main Screen: onCreate".
The else branch of the when statement handles any other lifecycle events that aren't
explicitly handled in the other branches. In this case, it logs "oh.. nooooo".
 */
@Composable
fun MainScreen() {
    ComposableLifecycle { source, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                Log.d(TAG, "MainScreen: onCreate")
            }

            Lifecycle.Event.ON_START -> {
                Log.d(TAG, "MainScreen: onStart")
            }
            Lifecycle.Event.ON_RESUME -> {
                Log.d("TAG", "MainScreen: ON_RESUME")
            }

            Lifecycle.Event.ON_PAUSE -> {
                Log.d("TAG", "MainScreen: ON_PAUSE")
            }

            Lifecycle.Event.ON_STOP -> {
                Log.d("TAG", "MainScreen: ON_STOP")
            }

            Lifecycle.Event.ON_DESTROY -> {
                Log.d("TAG", "MainScreen: ON_DESTROY")
            }
            else -> {
                //handler other lifecycle events
                Log.d(TAG, "oh.. nooooo")
            }
        }

    }
}

//current lifecycle owner
//fun <functionname>() { ...<body>...}
@Composable
fun ComposableLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event -> onEvent(lifecycleOwner, event) }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
/*
defines a ComposableLifecycle function that observes the lifecycle of a LifecycleOwner and
triggers a callback function when a lifecycle event occurs.

Here's a breakdown of the function:

@Composable is an annotation that indicates the function is a composable function.
Composable functions are the building blocks of Jetpack Compose, Android's modern
toolkit for building native UI.

fun ComposableLifecycle(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
onEvent: (LifecycleOwner, Lifecycle.Event)-> Unit) defines the function.
It takes two parameters: a LifecycleOwner (with a default value of the current lifecycle owner)
and a callback function onEvent that is triggered when a lifecycle event occurs.

DisposableEffect(lifecycleOwner) is a composable effect that performs some setup when entering the
composition and some cleanup when leaving the composition.
In this case, it's used to add and remove a lifecycle observer.

val observer = LifecycleEventObserver {_, event-> onEvent(lifecycleOwner, event)}
creates a LifecycleEventObserver that triggers the onEvent callback when a lifecycle event occurs.

lifecycleOwner.lifecycle.addObserver(observer) adds the observer to the lifecycle
of the LifecycleOwner.

onDispose{ lifecycleOwner.lifecycle.removeObserver(observer) } removes the observer from the
lifecycle when the composable leaves the composition. This is important to prevent memory leaks.
 */


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
    SimpleLifecycleTheme {
        Greeting("Android")
    }
}