package com.example.kotlin_firebase.data

import com.example.kotlin_firebase.data.Note
import kotlinx.coroutines.flow.Flow
import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class NotesRepository(private val database: FirebaseDatabase) {
    private val notesRef: DatabaseReference = database.getReference("notes")


    fun getNote(id: Int): Flow<Note?> = callbackFlow {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val note = snapshot.getValue(Note::class.java)
                trySend(note).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        notesRef.child(id.toString()).addValueEventListener(valueEventListener)
        awaitClose {
            notesRef.child(id.toString()).removeEventListener(valueEventListener)
        }
    }


    fun insertNote(note: Note) {
        notesRef.child("1").setValue(note)
    }

    fun deleteNote(note: Note) {
        notesRef.child(note.id.toString()).removeValue()
    }

}