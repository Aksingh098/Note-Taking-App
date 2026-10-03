package com.example.notetakingapp.data.repository

import com.example.notetakingapp.data.local.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun addNote(note: Note)

    fun getNotes(): Flow<List<Note>>


}
