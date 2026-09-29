package com.example.notetakingapp.data.repository

import com.example.notetakingapp.data.local.Note
import com.example.notetakingapp.data.local.NoteDao

class NotesRepository(private val dao: NoteDao)
//val -> make it a property that cannot be reassigned
{

    fun addNote(note: Note) = dao.insertNote(note)
}