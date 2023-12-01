package com.example.audio

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.ui.theme.AudioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            AudioPlay(this)
        }
    }
}

@Composable
fun AudioPlay(context: Context) {
    val mp: MediaPlayer = MediaPlayer.create(context, R.raw.sippingcoffee)
    Column(modifier = Modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Image(painter = painterResource(id = R.drawable.coffeeimage),
                contentDescription = "profilePic",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(100.dp)
            )
            IconButton(onClick = { mp.start() }) {
                Icon(Icons.Filled.PlayArrow, contentDescription = "play button", modifier = Modifier.size(150.dp))
            }
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(1.dp)
                    .background(color = Color.Black)
            )
            IconButton(onClick = { mp.pause() }) {
                Icon(Icons.Filled.Star, contentDescription = "pause button", modifier = Modifier.size(150.dp))
            }
        }
        Text("This is the sound of sipping coffee.", fontSize = 15.sp)
    }
}

