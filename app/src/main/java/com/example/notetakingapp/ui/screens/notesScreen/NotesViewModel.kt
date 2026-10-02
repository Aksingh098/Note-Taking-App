package com.example.notetakingapp.ui.screens.notesScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.notetakingapp.NoteMakingApplication
import com.example.notetakingapp.data.local.Note
import com.example.notetakingapp.data.repository.NotesRepository
import com.example.notetakingapp.data.repository.OfflineNotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(private val repository: NotesRepository): ViewModel() {

    fun addNote(title: String, content: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.addNote(Note(title = title, content = content))
    }

    val notes: StateFlow<List<Note>> = repository.getNotes().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000), // Keep Flow active while UI is subscribed; stop after 5 sec
        initialValue = emptyList() // Initial value before data is emitted
    )

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =(this[APPLICATION_KEY] as NoteMakingApplication)
                NotesViewModel(application.container.notesRepository )
            }
        }
    }
}


