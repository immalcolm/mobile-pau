package com.pau.simpleinteractionstatecomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pau.simpleinteractionstatecomposedemo.ui.theme.SimpleInteractionStateComposeDemoTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleInteractionStateComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        //verticalArrangement = Arrangement.Center,
                        //horizontalAlignment = Alignment.CenterHorizontally){
                        Column() {
                            PressIconButton(
                                onClick = {},
                                icon = {
                                    Icon(
                                        Icons.Filled.ShoppingCart,
                                        contentDescription = null
                                    )
                                },
                                text = { Text("Add to Cart (Press and Hold)") }
                            )
                            PressIconButtonTap(
                                onClick = {},
                                icon = {
                                    Icon(
                                        Icons.Filled.ShoppingCart,
                                        contentDescription = null
                                    )
                                },
                                text = { Text("Add to Cart (Show and Hide 3sec)") }
                            )
                            PressIconButtonShowHide(
                                onClick = {},
                                icon = {
                                    Icon(
                                        Icons.Filled.ShoppingCart,
                                        contentDescription = null
                                    )
                                },
                                text = { Text("Add to Cart (Show and Hide by Tap)") }
                            )
                        }
                    }

                }
            }
        }
    }
}

//when button is pressed, icon to appear
//custom button feature
//compose UI that allows clickable, icon, text, modifier,
//READ
//Button: https://developer.android.com/jetpack/compose/components/button
@Composable
fun PressIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier, //allow to apply modifier to PressIconButton
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() } //interaction source
) {
    //let's define what happens when content is passed in
    //collect the pressed stated from the interaction source
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource
    ) {
        //show the icon only when the button is pressed
        //press and "hold"
        AnimatedVisibility(visible = isPressed) {
            if (isPressed) {
                //draw/display the icon from the parameter and add spacing
                //create some new UI
                Row {
                    icon() //icon that came from param
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                }
            }
        }
        //display text UI from param
        text()
    }
}

@Preview
@Composable
fun PreviewPressIconButton() {
    PressIconButton(
        onClick = {},
        icon = {
            Icon(
                Icons.Filled.ShoppingCart,
                contentDescription = null
            )
        },
        text = { Text("Add to Cart") }
    )
}

@Composable
fun PressIconButtonTap(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier, //allow to apply modifier to PressIconButton
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() } //interaction source
) {
    //let's define what happens when content is passed in
    //collect the pressed stated from the interaction source
    //val isPressed by interactionSource.collectIsPressedAsState()
    var showIcon by remember { mutableStateOf(false) } //start with false. this remembers our icon state
    var clickCount by remember { mutableStateOf(0) }

    LaunchedEffect(clickCount) {
        if(showIcon) {
            delay(3000) //adjust if needed
            showIcon = false
        }
    }
    Button(
        onClick = {
            onClick
            showIcon = true //change the icon status
            //simulate release delay
            clickCount++

        },
        modifier = modifier,
        interactionSource = interactionSource
    ) {
        //show the icon only when the button is pressed
        //press and "hold"
        //here we determine visibility of the icon based on the user click
        AnimatedVisibility(visible = showIcon) {
            //draw/display the icon from the parameter and add spacing
            //create some new UI
            Row {
                icon() //icon that came from param
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))

            }
        }
        //display text UI from param
        text()
    }
}

@Composable
fun PressIconButtonShowHide(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier, //allow to apply modifier to PressIconButton
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() } //interaction source
) {
    //let's define what happens when content is passed in
    //collect the pressed stated from the interaction source
    //val isPressed by interactionSource.collectIsPressedAsState()
    var showIcon by remember { mutableStateOf(false) } //start with false. this remembers our icon state

    Button(
        onClick = {
            onClick
            showIcon = !showIcon //change the icon status
            //simulate release delay
        },
        modifier = modifier,
        interactionSource = interactionSource
    ) {
        //show the icon only when the button is pressed
        //press and "hold"
        //here we determine visibility of the icon based on the user click
        AnimatedVisibility(visible = showIcon) {
            //draw/display the icon from the parameter and add spacing
            //create some new UI
            Row {
                icon() //icon that came from param
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))

            }
        }
        //display text UI from param
        text()
    }
}