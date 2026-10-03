package com.example.notetakingapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.notetakingapp.ui.screens.notesDetailScreen.NotesDetailScreen
import com.example.notetakingapp.ui.screens.notesScreen.NotesScreen

@Composable
fun NavGraph(){

    // navController -> handle navigation
    // rememberNavController() -> Create & remember the navController
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.NotesScreen
    ){
        composable<NavRoutes.NotesScreen> {
            NotesScreen(navController = navController)
        }

        composable<NavRoutes.NotesDetailScreen> {backStackEntry ->

            val data = backStackEntry.toRoute<NavRoutes.NotesDetailScreen>()

            NotesDetailScreen(
                title = data.noteTitle,
                content = data.noteContent,

            )
        }

    }
}