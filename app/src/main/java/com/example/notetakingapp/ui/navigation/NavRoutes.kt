package com.example.notetakingapp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoutes {

    @Serializable
    object NotesScreen: NavRoutes()

    @Serializable
    data class NotesDetailScreen(
        val noteTitle: String,
        val noteContent: String,
    ): NavRoutes()
}
