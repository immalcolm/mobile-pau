package com.pau.simplelazydemokotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pau.simplelazydemokotlin.ui.theme.SimpleLazyDemoKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleLazyDemoKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val sampleMessage = listOf(
                        Message(id = 1, text = "Hello", imageResId = R.drawable.profile_1),
                        Message(id = 2, text = "How are you", imageResId = R.drawable.profile_1),
                        Message(id = 3, text = "Pretty Awesome", imageResId = R.drawable.profile_1),
                    )
                    MessageList(messages = sampleMessage)
                }
            }
        }
    }
}

//Create lazy lists and grids
//@TODO upgrade with dynamic images from online

data class Message(val id: Int, val text: String, val imageResId: Int)

//takes in a Message class object
@Composable
fun MessageRow(message: Message) {
    Box(
        modifier = Modifier
            .background(Color.Gray)
            .border(2.dp, Color.Blue, RoundedCornerShape(8.dp))
            //.padding(bottom=20.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .clickable { /* handle click action */ }
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = message.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = message.text,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(
            modifier = Modifier
                .background(Color.Magenta)
                .width(20.dp)
                .fillMaxHeight()
                .align(Alignment.CenterStart)

        )
    }
}


//list of messages
@Composable
fun MessageList(messages: List<Message>) {
    //LazyColumn composable to efficiently display a scrollable list
    LazyColumn {
        items(messages) { message ->
            MessageRow(message = message)
        }
    }
}

@Preview
@Composable
fun PreviewMessageList() {
    val sampleMessage = listOf(
        Message(id = 1, text = "Hello", imageResId = R.drawable.profile_1),
        Message(id = 2, text = "How are you", imageResId = R.drawable.profile_1),
        Message(id = 3, text = "Pretty Awesome", imageResId = R.drawable.profile_1),
    )
    MessageList(messages = sampleMessage)
}