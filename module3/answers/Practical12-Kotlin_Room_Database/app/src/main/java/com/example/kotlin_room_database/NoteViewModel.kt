package com.example.kotlin_room_database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_room_database.data.Note
import com.example.kotlin_room_database.data.NotesRepository
import androidx.lifecycle.asLiveData
import com.example.kotlin_room_database.data.NoteDatabase

class NoteViewModel(
    application: Application
) : ViewModel() {

    private val notesRepository: NotesRepository

    // Using LiveData to observe changes in the single note
    val note: LiveData<Note?>

    init{
        val noteDatabase = NoteDatabase.getDatabase(application)
        val noteDao = noteDatabase.noteDao()
        notesRepository = NotesRepository(noteDao)
        note = notesRepository.getNote(1).asLiveData()

    }
    // Suspend function to insert a note in the database
    suspend fun insertNote(note: Note) {
        notesRepository.insertNote(note)
    }

    // Suspend function to delete a note from the database
    suspend fun deleteNote(note: Note?) {
        note?.let {
            notesRepository.deleteNote(it)
        }
    }
}
