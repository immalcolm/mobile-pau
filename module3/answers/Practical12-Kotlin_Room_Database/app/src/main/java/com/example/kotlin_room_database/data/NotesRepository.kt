package com.example.kotlin_room_database.data
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val noteDao: NoteDao) {
    fun getNote(id: Int): Flow<Note?> = noteDao.getNoteById(id)

    suspend fun insertNote(note: Note) = noteDao.insert(note)

    suspend fun deleteNote(note: Note) = noteDao.delete(note)
}