package com.example.kotlin_room_database

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlin_room_database.data.Note
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import kotlinx.coroutines.launch

import com.example.kotlin_room_database.ui.theme.Kotlin_Room_DatabaseTheme



class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kotlin_Room_DatabaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val owner = LocalViewModelStoreOwner.current
                    owner?.let {
                        val noteViewModel: NoteViewModel = viewModel(
                            it,
                            "NoteviewModel",
                            NoteViewModelFactory(
                                LocalContext.current.applicationContext as Application)
                            )

                        val currNote by noteViewModel.note.observeAsState()
                        MainScreen(noteViewModel = noteViewModel, currNote = currNote)
                    }


                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(noteViewModel: NoteViewModel, currNote: Note?) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            label = { Text("Enter your notes title here") }
        )

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(136.dp)
                .padding(bottom = 16.dp),
            label = { Text("Enter your notes content here") },
            maxLines = 5,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onDone = {
                // Handle keyboard done action if needed
            })
        )

        Button(
            onClick = {
                // Handle save button click
                noteViewModel.viewModelScope.launch {
                    noteViewModel.insertNote(Note(1, title, content))
                    title = ""
                    content = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = null) //default Save
            Spacer(modifier = Modifier.width(8.dp))
            Text("Save")
        }


        Text(
            text = currNote?.title ?: "Notes Title",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = currNote?.content ?: "Set up your notes content!",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    // Handle delete button click
                    noteViewModel.viewModelScope.launch {
                        noteViewModel.deleteNote(currNote)
                    }
                },
                modifier = Modifier
                    .wrapContentWidth()
            ) {
                Icon(Icons.Default.Delete, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Delete")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Kotlin_Room_DatabaseTheme {
        val owner = LocalViewModelStoreOwner.current
        owner?.let {
            val viewModel: NoteViewModel = viewModel(
                it,
                "NoteviewModel",
                NoteViewModelFactory(
                    LocalContext.current.applicationContext as Application)
            )

            val currNote by viewModel.note.observeAsState()
            MainScreen(noteViewModel = viewModel, currNote = currNote)
        }
    }
}

class NoteViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        return NoteViewModel(application) as T
    }
}
