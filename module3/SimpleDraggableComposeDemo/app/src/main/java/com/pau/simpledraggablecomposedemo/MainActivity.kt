package com.pau.simpledraggablecomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.pau.simpledraggablecomposedemo.ui.theme.SimpleDraggableComposeDemoTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleDraggableComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(){
                            DraggableText()
                            DraggableTextPointer()
                            DraggableButtonPointer()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DraggableText() {
    // State to track the horizontal offset due to drag
    var offsetX by remember { mutableStateOf(0f) }

    // Compose Text element with draggable behavior
    Text(
        modifier = Modifier
            .offset {
                IntOffset(
                    offsetX.roundToInt(),
                    0
                )
            } // Set the horizontal offset based on the state
            .draggable( //allows draggable in only one direction
                orientation = Orientation.Horizontal, // Allow only horizontal dragging
                state = rememberDraggableState { delta ->
                    offsetX += delta // Update the offset based on the drag delta
                }
            ),
        text = "Drag me!" // Displayed text in the draggable Text element
    )
}


@Composable
private fun DraggableTextPointer() {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Text(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            },
        text = "Drag me Anywhere!"
    )
}

@Composable
private fun DraggableButtonPointer() {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Button(
        onClick = {},
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    ){
        Text("Drag me Anywhere")
    }
}
