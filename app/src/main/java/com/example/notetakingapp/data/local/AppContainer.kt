package com.example.notetakingapp.data.local

import android.content.Context
import com.example.notetakingapp.data.repository.NotesRepository
import com.example.notetakingapp.data.repository.OfflineNotesRepository


interface AppContainer{
    val notesRepository: NotesRepository
}



class  DefaultAppContainer(private val context: Context) : AppContainer {

    override val notesRepository: NotesRepository by lazy{
        NotesDataBase.getDatabase(context).NotesDao().let { notesDao ->
            OfflineNotesRepository(notesDao)
        }
    }
}