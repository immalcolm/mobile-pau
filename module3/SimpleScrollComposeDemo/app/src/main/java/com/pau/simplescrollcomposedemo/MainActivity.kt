package com.pau.simplescrollcomposedemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pau.simplescrollcomposedemo.ui.theme.SimpleScrollComposeDemoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleScrollComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScrollBoxesSmooth()
                    //val coroutineScope = rememberCoroutineScope()
                    //ScrollableListExample(coroutineScope)
                }
            }
        }
    }
}

@Composable
fun ScrollBoxesSmooth(){
    //create a scroll state and smoothly scroll 100px on the first composition
    val state = rememberScrollState()

    //Use LaunchedEffect to perform an action on first compostion
    LaunchedEffect(Unit){
        //Animate the scroll state to 100 pixels
        state.animateScrollTo(1000)
    }

    //Define a column with background color, size constraints, padding and vertical scrolling
    Column(
        modifier = Modifier
        .background(Color.White)
        .size(100.dp)
        .padding(horizontal = 8.dp)
        .verticalScroll(state)
    ) {
        //Repeat the following content for each iteration
        //by default the "iteration index" is $it
        repeat(50){
            //Display a Text composable with padding and dynamic text based on the iteration index
            //Text("Item $it", modifier = Modifier.padding(2.dp))
            //to start at item 1 ${index+1}
            index -> Text("Item $index", modifier = Modifier.padding(2.dp))
        }
    }

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen() {
    val coroutineScope = rememberCoroutineScope()

    Scaffold {
        ScrollableListExample(coroutineScope)
    }
}

@Composable
fun ScrollableListExample(scope: CoroutineScope) {
    val listState = rememberLazyListState()
    var num: Int = remember { 0 }
    Column {
        Button(
            onClick = {
            scope.launch {
                listState.animateScrollToItem(index = num) // Scrolls to the 10th item
            }
            num += 10
        }) {
            Text("Scroll by 10 items")
        }

        LazyColumn(state = listState) {
            items(100) { index ->
                Text("Item ${index + 1}", modifier = Modifier.padding(4.dp))
            }
        }
    }
}