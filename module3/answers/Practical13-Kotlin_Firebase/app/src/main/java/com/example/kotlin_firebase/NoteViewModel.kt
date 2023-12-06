package com.example.kotlin_firebase

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.kotlin_firebase.data.NotesRepository
import com.example.kotlin_firebase.data.Note
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteViewModel(
    application: Application
) : ViewModel() {

    private val notesRepository: NotesRepository

    val database: FirebaseDatabase

    // Using LiveData to observe changes in the single note
    val note: LiveData<Note?>

    init{

        // Create reference to the database
        database = FirebaseDatabase.getInstance("https://fir-activity-83e5a-default-rtdb.asia-southeast1.firebasedatabase.app/")
        //myRef = database.getReference("notes")
        notesRepository = NotesRepository(database)

        // Create reference to the database

        note = notesRepository.getNote(1).asLiveData()

    }
    // Suspend function to insert a note in the database
    suspend fun insertNote(note: Note) {
        withContext(Dispatchers.IO) {
            notesRepository.insertNote(note)
        }
    }

    // Suspend function to delete a note from the database
    suspend fun deleteNote(note: Note?) {
        withContext(Dispatchers.IO) {
            note?.let {
                notesRepository.deleteNote(it)
            }
        }
    }
}
