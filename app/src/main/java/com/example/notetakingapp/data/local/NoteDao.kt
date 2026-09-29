package com.example.notetakingapp.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy


interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)
}