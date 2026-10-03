package com.example.notetakingapp.data.repository

import com.example.notetakingapp.data.local.Note
import com.example.notetakingapp.data.local.NoteDao
import kotlinx.coroutines.flow.Flow

class OfflineNotesRepository(private val dao: NoteDao): NotesRepository
//val -> make it a property that cannot be reassigned
{
    override suspend fun addNote(note: Note) = dao.insertNote(note)

    override fun getNotes(): Flow<List<Note>> = dao.getNotes()


}